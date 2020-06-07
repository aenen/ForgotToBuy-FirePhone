package com.dunno.aenen.forgottobuy;

import java.util.Date;

/**
 * Created by Yaroslav on 02.06.2020.
 */
public class ChecklistDTO {
    public long IdList;
    public String Title;
    public Date CreationDate;

    public ChecklistDTO(){}
    public ChecklistDTO(long idList, String title, Date creationDate) {
        IdList = idList;
        Title = title;
        CreationDate = creationDate;
    }
}
