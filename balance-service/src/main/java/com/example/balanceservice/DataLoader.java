package com.example.balanceservice;

import com.example.balanceservice.DAO.BalanceDAO;
import com.example.balanceservice.DAO.StreamBalanceDAO;
import com.example.balanceservice.Model.BalanceModel;
import com.example.balanceservice.Model.StreamBalanceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Transactional
public class DataLoader {
    @Autowired
    private BalanceDAO balanceDAO;

    @Autowired
    private StreamBalanceDAO streamBalanceDAO;

    @PostConstruct
    public void init(){
        //Balance Data
        BalanceModel b = new BalanceModel();
        b = balanceDAO.save( new BalanceModel(0,100001,201,10));
        System.out.println("\nBalance = " + b.toString());

        b = balanceDAO.save( new BalanceModel(0,100001,202,100));
        System.out.println("\nBalance = " + b.toString());

        b = balanceDAO.save( new BalanceModel(0,100001,203,19));
        System.out.println("\nBalance = " + b.toString());

        b = balanceDAO.save( new BalanceModel(0,100001,204,22));
        System.out.println("\nBalance = " + b.toString());

        b = balanceDAO.save( new BalanceModel(0,100001,205,24));
        System.out.println("\nBalance = " + b.toString());

        b = balanceDAO.save( new BalanceModel(0,100001,206,27));
        System.out.println("\nBalance = " + b.toString());

        b = balanceDAO.save( new BalanceModel(0,100002,201,22));
        System.out.println("\nBalance = " + b.toString());

        b = balanceDAO.save( new BalanceModel(0,100002,202,20));
        System.out.println("\nBalance = " + b.toString());

        b = balanceDAO.save( new BalanceModel(0,100002,203,25));
        System.out.println("\nBalance = " + b.toString());

        b = balanceDAO.save( new BalanceModel(0,100002,204,12));
        System.out.println("\nBalance = " + b.toString());

        b = balanceDAO.save( new BalanceModel(0,100002,205,14));
        System.out.println("\nBalance = " + b.toString());

        b = balanceDAO.save( new BalanceModel(0,100002,206,17));
        System.out.println("\nBalance = " + b.toString());

        // Stream Balance Data
        StreamBalanceModel sb = new StreamBalanceModel();

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T11:15:30"),
                1, 100001, "Long Sleeves", 201, "Irving","75063",  5, 10));
        System.out.println("\nSteam Balance = " + sb.toString());

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T12:15:30"),
                2, 100001, "Long Sleeves", 202, "Plano","75024", 95, 100));
        System.out.println("\nSteam Balance = " + sb.toString());

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T12:15:30"),
                3, 100001, "Long Sleeves", 203, "Dallas","75204", 14, 19));
        System.out.println("\nSteam Balance = " + sb.toString());

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T12:15:30"),
                4, 100001, "Long Sleeves", 204, "Irving", "75015",17, 22));
        System.out.println("\nSteam Balance = " + sb.toString());

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T12:15:30"),
                5, 100001, "Long Sleeves", 205, "Coppell", "75019",19, 24));
        System.out.println("\nSteam Balance = " + sb.toString());

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T12:15:30"),
                6, 100001, "Long Sleeves", 206, "Coppell", "75099",22, 27));
        System.out.println("\nSteam Balance = " + sb.toString());

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T12:15:30"),
                7, 100002, "Short Sleeves", 201, "Irving","75063",17, 22));
        System.out.println("\nSteam Balance = " + sb.toString());

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T12:15:30"),
                8, 100002, "Short Sleeves", 202, "Plano","75024",15, 20));
        System.out.println("\nSteam Balance = " + sb.toString());

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T12:15:30"),
                9, 100002, "Short Sleeves", 203, "Dallas","75204",20, 25));
        System.out.println("\nSteam Balance = " + sb.toString());

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T12:15:30"),
                10, 100002, "Short Sleeves", 204, "Irving", "75015",7, 12));
        System.out.println("\nSteam Balance = " + sb.toString());

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T12:15:30"),
                11, 100002, "Short Sleeves", 205, "Coppell", "75019",9, 14));
        System.out.println("\nSteam Balance = " + sb.toString());

        sb = streamBalanceDAO.save( new StreamBalanceModel(0, LocalDateTime.parse("2021-01-11T12:15:30"),
                12, 100002, "Short Sleeves", 206, "Coppell", "75099",12, 17));
        System.out.println("\nSteam Balance = " + sb.toString());
    }
}
