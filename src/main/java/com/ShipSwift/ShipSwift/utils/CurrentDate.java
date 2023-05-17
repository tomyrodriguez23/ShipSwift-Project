package com.ShipSwift.ShipSwift.utils;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
public class CurrentDate {

    public static LocalDate getNow(){
        LocalDateTime now = LocalDateTime.now();
        return now.toLocalDate();
    }

}
