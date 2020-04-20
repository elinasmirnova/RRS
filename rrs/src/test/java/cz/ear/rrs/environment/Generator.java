package cz.ear.rrs.environment;


import cz.ear.rrs.model.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Generator {

    private static final Random RAND = new Random();


    public static int randomInt() {
        return RAND.nextInt();
    }

    public static boolean randomBoolean() {
        return RAND.nextBoolean();
    }

    public static Account generateUser() {
        final Account user = new Account();
//        ArrayList<Building> buildings = new ArrayList<>();
//        buildings.add(generateBuilding());
//        buildings.add(generateBuilding());
//        user.setId(randomInt()*randomInt()*randomInt());
        user.setFirstname("FirstName" + randomInt());
        user.setLastname("LastName" + randomInt());
        user.setUsername("username" + randomInt() + "@");
        user.setPassword(Integer.toString(randomInt()));
//        user.setBuildings(buildings);
        user.addBuilding(generateBuilding());
        user.setRights(Rights.TIER1);
        return user;
    }

    public static Reservation generateReservation() {
        final Reservation reservation = new Reservation();

        reservation.setDate(GeneratorUtils.createRandomDate());
        LocalTime randomTimeFrom = GeneratorUtils.createRandomTimeFrom();
        reservation.setStart_At(randomTimeFrom);
        reservation.setEnd_At(GeneratorUtils.createRandomTimeTo(randomTimeFrom));
        reservation.setRoom(generateRoom());
        reservation.setAccount(generateUser());

        return reservation;

    }

    public static Building generateBuilding() {
        final Building building = new Building();

//        building.setId(randomInt()*randomInt()*randomInt());
        building.setAddress("Address" + randomInt());

        return building;
    }

    public static Room generateRoom() {
        final Room room = new Room();

//        room.setId(randomInt()*randomInt()*randomInt());
        room.setCapacity((int) (Math.random() * ((50 - 10) + 1)) + 10);
        room.setLocation("Location" + randomInt());
        room.setName("Name" + randomInt());
        room.setRights(Rights.TIER1);
        room.setBuilding(generateBuilding());

        return room;
    }

    public static Equipment generateEquipment() {
        final Equipment equipment = new Equipment();

//        equipment.setName();
//        equipment.setLocation();
//        equipment.setTotalAmount();
//        equipment.setRights();

        return equipment;
    }


}
