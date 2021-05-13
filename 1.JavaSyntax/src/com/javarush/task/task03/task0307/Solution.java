package com.javarush.task.task03.task0307;

/* 
Привет StarCraft!
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Zerg zerg1 = new Zerg();
        zerg1.name = "zerg1";
        Zerg zerg2 = new Zerg();
        zerg2.name = "zerg2";
        Zerg zerg3 = new Zerg();
        zerg3.name = "zerg3";
        Zerg zerg4 = new Zerg();
        zerg4.name = "zerg4";
        Zerg zerg5 = new Zerg();
        zerg5.name = "zerg5";

        Protoss protos1 = new Protoss();
        protos1.name = "protos1";
        Protoss protos2 = new Protoss();
        protos2.name = "protos2";
        Protoss protos3 = new Protoss();
        protos3.name = "protos3";

        Terran terran1 = new Terran();
        terran1.name = "terran1";
        Terran terran2 = new Terran();
        terran2.name = "terran2";
        Terran terran3 = new Terran();
        terran3.name = "terran3";
        Terran terran4 = new Terran();
        terran4.name = "terran4";

    }

    public static class Zerg {
        public String name;
    }

    public static class Protoss {
        public String name;
    }

    public static class Terran {
        public String name;
    }
}
