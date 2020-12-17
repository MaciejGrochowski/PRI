export const textsPolish = {
    noRecordsOnCharactersList: 'Brak postaci spełniających kryteria',
    nameValidationText: 'Imię powinno zaczynać się z wielkiej litery i zawierać litery A-Z.',
    surnameValidationText: 'Poprawne formy nazwisk to: Von Munder | von Munder | Munder.',
    dayOfBornValidationText: 'Dzień urodzenia może mieć tylko wartość 1-34.',
    yearOfBornValidationText: 'Rok urodzenia może mieć tylko wartość 0-3000.',
    heightValidationText: 'Wzrost może mieć tylko wartość 50-300.',
    weightValidationText: 'Waga może mieć tylko wartość 10-800.',
    toolTipName: "By wygenerować imię musisz uzupełnić pola: ",
    toolTipSurname: "By wygenerować nazwisko musisz uzupełnić pola: ",
    toolTipRace: "By wygenerować rase musisz uzupełnić pole: ",
    toolTipSex: "By wygenerować płeć musisz uzupełnić pole: ",
    toolTipProfession: "By wygenerować profesje musisz uzupełnić pola: ",
    toolTipPlaceOfBeing: "By wygenerować miejsce pobytu musisz uzupełnić pola: ",
    toolTipDayOfBorn: "By wygerować całą date urodzenia musisz uzupełnić pola: ",
    toolTipMonthOfBorn: "By wygerować całą date urodzenia musisz uzupełnić pola: ",
    toolTipYearOfBorn: "By wygerować całą date urodzenia musisz uzupełnić pole: ",
    toolTipHeight: "By wygenerować wzrost musisz uzupełnić pola: ",
    toolTipWeight: "By wygenerować wage musisz uzupełnić pola: ",
    toolTipColorOfEyes: "By wygenerować kolor oczu musisz uzupełnić pole: ",
    toolTipColorOfHair: "By wygenerować kolor włosów musisz uzupełnić pole: ",
    toolTipCharacter: "By wygenerować cechy charakteru musisz uzupełnić pola:",
    toolTipAppearance: "By wygenerować cechy wyglądu musisz uzupełnić pola: ",
    toolTipPreviousProfession: "Poprzednie profesje zmieniają się przy losowaniu obecnej profesji",
    toolTipReligion: "By wygerować religie potrzeba: ",
    toolTipEmotions: "By wygenerować dominujące emocje musisz uzupełnić pola: ",
    toolTipSkills: "By wygenerować umiejętności musisz uzupełnić pola: ",
    toolTipTalents: "By wygenerować zdolności musisz uzupełnić pola: ",
    noRecordsOnHistoryList: "Brak historii spełniających kryteria",
    needLoginToSaveCharacter: "Aby zapisać postać w bazie danych, musisz być zalogowany.",
    needLoginToSaveHistory: "Aby zapisać historię, musisz być zalogowany.",
    register: {
        username: 'Nazwa użytkownika*',
        password: 'Hasło*',
        confirmPassword: 'Powtórz hasło*',
        facebook: 'Link do facebooka',
        discord: 'Discord',
        description: 'Opis profilu',
        mail: 'Adres e-mail*',
        notSamePasswords: 'Hasła nie są takie same.'
    },
    registerTooltipTexts: {
        username: 'Nazwa użytkownika może posiadać łącznie 3-20 liter, liczb i znaków specjalnych.',
        password: 'Hasło musi zawierać 3-20 znaków dużą literę i znak specjalny.',
        facebook: 'Link do profilu na Facebooku',
        discord: 'Nazwa użytkownika Discord np. Ork#1234',
        description: 'Opis profilu może mieć maksymalnie 1000 znaków.',
        mail: 'Adres e-mail',
    }
};

export const polishCodeErrors = {
    NO_NAME: 'Postać musi mieć imię.',
    NAME_NOT_REGEXP: 'Imię postaci musi się składać z liter.',
    NO_BIRTH_PLACE: 'Postać musi mieć miejsce urodzenia.',
    NO_RACE: 'Postać musi mieć rasę.',
    RACE_NOT_REGEXP: 'Rasą postaci musi być człowiek, elf, krasnolud lub niziołek.',
    NO_EYE_COLOR: 'Postać musi mieć kolor oczu.',
    NO_HAIR_COLOR: 'Postać musi mieć kolor włosów.',
    NO_DAY_OF_BIRTH: 'Postać musi mieć dzień urodzenia.',
    DAY_OF_BIRTH_NOT_REGEXP: 'Dzień urodzenia postaci musi być jedno lub dwucyfrową liczbą.',
    NO_MONTH_OF_BIRTH: 'Postać musi mieć miesiąc urodzenia.',
    MONTH_OF_BIRTH_NOT_REGEXP: 'Miesiąc urodzenia postaci musi istnieć w świecie Warhammera.',
    NO_YEAR_OF_BIRTH: 'Postać musi mieć rok urodzenia.',
    YEAR_OF_BIRTH_NOT_REGEXP: 'Rok urodzenia postaci musi być maksymalnie czterocyfrową liczbą.',
    NO_SEX: 'Postać musi mieć płeć.',
    SEX_NOT_REGEXP: 'Postać musi być mężczyzną lub kobietą.',
    NO_RELIGION: 'Postać musi mieć religię.',
    NO_WEIGHT: 'Postać musi mieć wagę.',
    WEIGHT_NOT_REGEXP: 'Waga musi być dwu lub trzycyfrową liczbą.',
    NO_HEIGHT: 'Postać musi mieć wzrost.',
    HEIGHT_NOT_REGEXP: 'Wzrost postaci musi być dwu lub trzycyfrową liczbą.',
    NO_CURRENT_CAREER: 'Postać musi mieć bieżącą profesję.',
    NO_SKILLS: 'Postać musi mieć umiejętności.',
    NO_TALENTS: 'Postać musi mieć zdolności.',
    NO_WEAPON_SKILLS: 'Postać musi mieć wartość Walki Wręcz.',
    NO_BALLISTIC_SKILLS: 'Postać musi mieć wartość Umiejętości Strzeleckich.',
    NO_STRENGTH: 'Postać musi mieć wartość Krzepy.',
    NO_TOUGHNESS: 'Postać musi mieć wartość Odporności.',
    NO_AGILITY: 'Postać musi mieć wartość Zręczności.',
    NO_INTELLIGENCE: 'Postać musi mieć wartość Inteligencji.',
    NO_WILLPOWER: 'Postać musi mieć wartość Siły Woli.',
    NO_FELLOWSHIP: 'Postać musi mieć wartość Ogłady',
    NO_ATTACKS: 'Postać musi mieć wartość Ataków.',
    NO_WOUNDS: 'Postać musi mieć wartość Żywotności.',
    NO_MAGIC: 'Postać musi mieć wartość Magii.',
    NO_MOVEMENT: 'Postać musi mieć wartość Szybkości.',
    NO_LIVE_PLACE: 'Postać musi mieć Miejsce Pobytu.',
    WEAPON_SKILLS_NOT_REGEXP: 'Walka wręcz musi być liczbą z przedziału 1-99.',
    BALLISTIC_SKILLS_NOT_REGEXP: 'Umiejętności strzeleckie muszą być liczbą z przedziału 1-99.',
    STRENGTH_NOT_REGEXP: 'Krzepa musi być liczbą z przedziału 1-99.',
    TOUGHNESS_NOT_REGEXP: 'Odporność musi być liczbą z przedziału 1-99.',
    AGILITY_NOT_REGEXP: 'Zręczność musi być liczbą z przedziału 1-99.',
    INTELLIGENCE_NOT_REGEXP: 'Inteligencja musi być liczbą z przedziału 1-99.',
    WILLPOWER_NOT_REGEXP: 'Siła Woli musi być liczbą z przedziału 1-99.',
    FELLOWSHIP_NOT_REGEXP: 'Ogłada musi być liczbą z przedziału 1-99.',
    ATTACKS_NOT_REGEXP: 'Ataki muszą być liczbą z przedziału 1-99.',
    WOUNDS_NOT_REGEXP: 'Żywotność musi być liczbą z przedziału 1-99.',
    MAGIC_NOT_REGEXP: 'Magia musi być liczbą z przedziału 0-99.',
    MOVEMENT_NOT_REGEXP: 'Szybkość musi być liczbą z przedziału 1-99.',

    NO_HISTORY_DAY: 'Historia musi mieć dzień.',
    DAY_OF_HISTORY_NOT_REGEXP: 'Dzień historii musi być liczbą jedno lub dwucyfrową.',
    NO_MONTH_OF_HISTORY: 'Historia musi mieć miesiąc.',
    MONTH_OF_HISTORY_NOT_REGEXP: 'Miesiąc historii musi istnieć w świecie Warhammera.',
    NO_YEAR_OF_HISTORY: 'Historia musi mieć rok.',
    YEAR_OF_HISTORY_NOT_REGEXP: 'Rok historii musi być maksymalnie czterocyfrową liczbą.',
    NO_HISTORY_PLACE: 'Historia musi mieć miejsce.',
    NO_HISTORY_DESCRIPTION: 'Historia musi mieć opis.',
    NO_HISTORY_TITLE: 'Historia musi mieć tytuł.',

    registerErrors: {
        usernameTooShort: 'Nazwa użytkownika jest zbyt krótka.',
        usernameTooLong: 'Nazwa użytkownika jest zbyt długa.',
        emailNotCorrect: 'Podaj poprawny adres email.',
        passwordTooShort: 'Hasło jest zbyt krótkie.',
        passwordTooLong: 'Hasło jest zbyt długie.',
        facebookNotCorrect: 'Podaj poprawny adres do profilu na facebooku.',
        discordNotCorrect: 'Podaj poprawną nazwę użytkownika discord.',
        descriptionTooLong: 'Opis jest zbyt długi.'
    }
}