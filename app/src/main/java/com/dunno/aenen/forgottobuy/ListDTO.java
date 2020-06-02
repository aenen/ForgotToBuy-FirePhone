package com.dunno.aenen.forgottobuy;

import java.util.Date;

/**
 * Created by Yaroslav on 02.06.2020.
 */
public class ListDTO {
    public long IdList;
    public String Title;
    public Date CreationDate;

    public ListDTO(long idList, String title, Date creationDate) {
        IdList = idList;
        Title = title;
        CreationDate = creationDate;
    }
}
