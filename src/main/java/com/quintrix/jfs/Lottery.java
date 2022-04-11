package com.quintrix.jfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottery {

  public static void main(String[] argv) {
    System.out.println("It's time to play the lottery!");

    // populate an array of players to play the lottery
    List<LuckyNumbers> players = new ArrayList<>();
    players.add(new LuckyNumbers(1, "Dustin"));
    players.add(new LuckyNumbers(7, "Brandon"));
    players.add(new LuckyNumbers(12, "Drake"));
    players.add(new LuckyNumbers(13, "James"));
    players.add(new LuckyNumbers(20, "Gabrielle"));
    players.add(new LuckyNumbers(28, "Hamilton"));
    players.add(new LuckyNumbers(32, "Wenzel"));
    players.add(new LuckyNumbers(35, "Miller"));
    players.add(new LuckyNumbers(39, "Deante"));
    players.add(new LuckyNumbers(42, "Grahmet"));
    players.add(new LuckyNumbers(47, "Terry"));
    players.add(new LuckyNumbers(50, "Kit"));
    players.add(new LuckyNumbers(55, "Marianne"));
    players.add(new LuckyNumbers(64, "Steven"));
    players.add(new LuckyNumbers(69, "Tyler"));
    players.add(new LuckyNumbers(77, "Pharoh"));
    players.add(new LuckyNumbers(82, "Sunil"));
    players.add(new LuckyNumbers(99, "Generra"));


    // generate a stream of random numbers and put the top 20 into a new list
    Stream<Integer> randomNumbers = Stream.generate(() -> (new Random()).nextInt(100));
    List<Integer> topNums = randomNumbers.limit(20).collect(Collectors.toList());

    // print the lottery numbers
    System.out.println("Here are today's lucky numbers:");
    topNums.stream().sorted().forEach(System.out::println);

    // see if we have any winners
    long winners =
        players.stream().filter(s -> topNums.stream().anyMatch(p -> p == s.getNum())).count();
    System.out.println("Today we have " + winners + " winners! Congratulations to:");
    players.stream().filter(s -> topNums.stream().anyMatch(p -> p == s.getNum()))
        .forEach(s -> System.out.println(s.getName()));

  }
}
