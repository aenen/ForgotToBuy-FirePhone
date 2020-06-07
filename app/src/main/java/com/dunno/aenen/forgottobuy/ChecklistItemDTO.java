package com.dunno.aenen.forgottobuy;

import java.util.Date;

/**
 * Created by Yaroslav on 08.06.2020.
 */
public class ChecklistItemDTO {
    public long IdChecklistItem;
    public String Name;
    public int Sequence;
    public boolean IsChecked;

    public ChecklistItemDTO(){}
    public ChecklistItemDTO(long idChecklistItem, String name, int sequence, boolean isChecked) {
        IdChecklistItem = idChecklistItem;
        Name = name;
        Sequence = sequence;
        IsChecked = isChecked;
    }
}
