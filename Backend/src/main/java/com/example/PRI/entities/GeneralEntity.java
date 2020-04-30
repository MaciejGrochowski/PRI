package com.example.PRI.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class GeneralEntity implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this create id nr separate for each table
    private long id;

    public static final String DISCRIMINATOR_COLUMN = "classname";

    public GeneralEntity copy(long userId){
        GeneralEntity cloned = null;
        try {
            cloned = (GeneralEntity) this.clone();
            cloned.setId(0l);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloned;
    }

    public GeneralEntity getParent() {
        return null;
    }

    public List<GeneralEntity> getChildrenList() {
        return new ArrayList<GeneralEntity>();
    }

    public List<GeneralEntity> getParentList() {
        List<GeneralEntity> parentList = new ArrayList<>();
        GeneralEntity parent = this.getParent();
        do {
            if (parent != null) {
                parentList.add(parent);
                parent = parent.getParent();
            }
        } while (parent != null);
        return parentList;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            GeneralEntity that = (GeneralEntity) o;
            return Objects.equals(this.id, that.id);
        } else {
            return false;
        }
    }

    public String getCaption() {
        return new StringBuilder()
                .append(" [")
                .append(id)
                .append("]")
                .toString();
    }



}