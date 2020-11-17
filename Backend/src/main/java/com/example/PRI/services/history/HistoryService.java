package com.example.PRI.services.history;

import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.converters.HistoryConverter;
import com.example.PRI.dtos.characters.AutocompleteFilterCharactersOutputDto;
import com.example.PRI.dtos.characters.CharacterDefaultAttributesOutputDto;
import com.example.PRI.dtos.characters.CharacterListOutputDto;
import com.example.PRI.dtos.characters.CharacterTagOutputDto;
import com.example.PRI.dtos.histories.*;
import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Name;
import com.example.PRI.entities.character.Surname;
import com.example.PRI.entities.history.History;
import com.example.PRI.enums.Month;
import com.example.PRI.enums.Race;
import com.example.PRI.repositories.ImperialDateRepository;
import com.example.PRI.repositories.history.HistoryRepository;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.ImperialDateService;
import com.example.PRI.services.PlaceService;
import com.example.PRI.services.character.CharacterService;
import com.example.PRI.services.character.CharacterSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.PRI.converters.HistoryConverter.convertDetails;

@Service
public class HistoryService extends GeneralService {

    @Autowired
    PlaceService placeService;

    @Autowired
    CharacterService characterService;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    ImperialDateService imperialDateService;

    public AutocompleteFiltersHistoriesOutputDto getAutoCompletes(){
        AutocompleteFiltersHistoriesOutputDto output = new AutocompleteFiltersHistoriesOutputDto();
        output.setCharacterNames(getCharactersTags().stream().map(CharacterTagOutputDto::getText).collect(Collectors.toList()));
        output.setPlaceNames(placeService.getAllNames().stream().sorted().collect(Collectors.toList()));
        output.setMonthNames(Arrays.stream(Month.values()).map(Month::getMonthName).collect(Collectors.toList()));
        return output;
    }


    public HistoryListOutputDto getSomeHistoriesPaged(HistoryListFilterInputDto requestInfo) {
        HistoryListOutputDto output = new HistoryListOutputDto();
//        produceTestHistories();



        if(requestInfo.getSortedBy() != null && requestInfo.getSortedBy().equals("historyDay")) requestInfo.setSortedBy("date.day");
        if(requestInfo.getSortedBy() != null && requestInfo.getSortedBy().equals("historyMonth")) requestInfo.setSortedBy("date.month");
        if(requestInfo.getSortedBy() != null && requestInfo.getSortedBy().equals("historyYear")) requestInfo.setSortedBy("date.year");
        //ToDo refactor^


        Pageable pageable;
        if (requestInfo.getSortedBy() == null)
            pageable = PageRequest.of(requestInfo.getCurrentPage(), requestInfo.getRowsPerPage());
        else {
            if (requestInfo.getIsAscending())
                pageable = PageRequest.of(requestInfo.getCurrentPage(),
                        requestInfo.getRowsPerPage(), Sort.by(requestInfo.getSortedBy()).ascending());
            else
                pageable = PageRequest.of(requestInfo.getCurrentPage(),
                        requestInfo.getRowsPerPage(), Sort.by(requestInfo.getSortedBy()).descending());
        }

        Specification<History> specifications = this.getSpecificationsFromFilter(requestInfo);
        Page<History> historyFilteredPage = historyRepository.findAll(specifications, pageable);



        List<HistoryOutputDto> outputData = new ArrayList<>();
        historyFilteredPage.get().forEach(c -> outputData.add(HistoryConverter.convert(c)));

        output.setList(outputData);
        output.setTotalCount(historyFilteredPage.getTotalElements());

        return output;
    }

    private Specification<History> getSpecificationsFromFilter(HistoryListFilterInputDto requestInfo) {
        Specification<History> specifications = HistorySpecifications.getAll();
        if (requestInfo.getFilters() == null || requestInfo.getFilters().size() == 0) return specifications;

//
        if (requestInfo.getFilters().containsKey("historyDay")) {
            int day = Integer.parseInt(requestInfo.getFilters().get("historyDay"));
            specifications = specifications.and(HistorySpecifications.getByHistoryDay(day));
        }

        if (requestInfo.getFilters().containsKey("historyYear")) {
            int year = Integer.parseInt(requestInfo.getFilters().get("historyYear"));
            specifications = specifications.and(HistorySpecifications.getByHistoryYear(year));
        }

        if (requestInfo.getFilters().containsKey("historyMonth")) {
            String historyMonthData = requestInfo.getFilters().get("historyMonth");
            List<String> historyMonthListString = new ArrayList<>(Arrays.asList(historyMonthData.split(",")));
            List<Month> months = historyMonthListString.stream().map(Month::findByMonthName).collect(Collectors.toList());

            if(months.size() > 0)
            specifications = specifications.and(HistorySpecifications.getByMonthsIn(months));
            else return specifications.and(HistorySpecifications.GetNoone());
        }


        if (requestInfo.getFilters().containsKey("historyFilterPlace")) {
            String historyFilterPlaceData = requestInfo.getFilters().get("historyFilterPlace");
            List<String> historyFilterListString = new ArrayList<>(Arrays.asList(historyFilterPlaceData.split(",")));
            List<Place> historyPlaces = placeService.findByNameIn(historyFilterListString);
            if (historyPlaces.size() > 0)
                specifications = specifications.and(HistorySpecifications.getByPlaces(historyPlaces));
            else return specifications.and(HistorySpecifications.GetNoone());
        }


        if(requestInfo.getFilters().containsKey("historyFilterCharacters")){
            String historyFilterCharactersData = requestInfo.getFilters().get("historyFilterCharacters");
//            if(historyFilterCharactersData.contains("#")) historyFilterCharactersData = historyFilterCharactersData.substring(historyFilterCharactersData.lastIndexOf("#"));

            specifications = specifications.and(HistorySpecifications.getByCharacterInDescription(historyFilterCharactersData));

        }



        return specifications;
//
//        if (requestInfo.getFilters().containsKey("surname")) {
//            Optional<Surname> surname = surnameService.findBySurname(requestInfo.getFilters().get("surname"));
//            if (surname.isPresent())
//                specifications = specifications.and(CharacterSpecifications.getBySurname(surname.get()));
//            else return specifications.and(CharacterSpecifications.GetNoone());
//        }
//
//        if (requestInfo.getFilters().containsKey("race")) {
//            List<String> racesList = Arrays.asList(requestInfo.getFilters().get("race").split(","));
//            List<Race> races = racesList.stream().map(Race::valueOf).collect(Collectors.toList());
//            if(races.size() > 0) specifications = specifications.and(CharacterSpecifications.getByRaces(races));
//            else return specifications.and(CharacterSpecifications.GetNoone());
//        }
    }

    public List<HistoryListCharacterDetailsOutputDto> getFirstHistoriesOfCharacter(String inputCharacter){
        List<HistoryListCharacterDetailsOutputDto> output = new ArrayList<>();
        Pageable pageable;
        pageable = PageRequest.of(0, 10, Sort.by("createdDate").descending());

        Specification<History> specifications = HistorySpecifications.getAll();
        specifications = specifications.and(HistorySpecifications.getByCharacterInDescription(inputCharacter));

        Page<History> historyFilteredPage = historyRepository.findAll(specifications, pageable);

        historyFilteredPage.get().forEach(c -> output.add(HistoryConverter.convertForCharacterDetails(c)));

        return output;

    }

    public List<CharacterTagOutputDto> getCharactersTags() {
        return characterService.getDataForTags();
    }

    public long save(HistoryInputDto historyInputDto) {
        History newHistory = new History();
        ImperialDate imperialDate = new ImperialDate();

        imperialDate.setDay(Integer.valueOf(historyInputDto.getDay()));
        imperialDate.setYear(Integer.valueOf(historyInputDto.getYear()));
        imperialDate.setMonth(Month.findByMonthName(historyInputDto.getMonth()));
        imperialDate = imperialDateService.save(imperialDate);
        newHistory.setDate(imperialDate);

        Optional<Place> place = placeService.findByName(historyInputDto.getPlace());
        place.ifPresent(newHistory::setPlace);

        newHistory.setCreatedDate(new Date());
        newHistory.setDescription(historyInputDto.getDescription());

        newHistory = historyRepository.save(newHistory);

        return newHistory.getId();


    }

    public HistoryDetailsOutputDto getDetailsById(Long id) {
        Optional<History> optionalHistory = historyRepository.findById(id);
        HistoryDetailsOutputDto output = new HistoryDetailsOutputDto();
        if(optionalHistory.isPresent()){
            output = convertDetails(optionalHistory.get());
        }
        return output;
    }
}
