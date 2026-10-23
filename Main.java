package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList; // TAMBAHAN

public class Main {

    public static void main(String[] args) {

        CharacterManager manager = new CharacterManager();

        Character c1 = new Character("Aria", CharacterType.WARRIOR, 5);
        Character c2 = new Character("Luna", CharacterType.MAGE, 3);
        Character c3 = new Character("Raven", CharacterType.ARCHER, 4);
        Character c4 = new Character("Aria", CharacterType.ROGUE, 2);

        System.out.println("=== CHARACTER MANAGER TP5 ===");

        System.out.println("\n--- Tambah Karakter ---");
        System.out.println("Tambah Aria: " + manager.addCharacter(c1));
        System.out.println("Tambah Luna: " + manager.addCharacter(c2));
        System.out.println("Tambah Raven: " + manager.addCharacter(c3));

        System.out.println("\n--- Uji Nama Duplikat ---");
        System.out.println("Tambah Aria lagi: " + manager.addCharacter(c4));

        System.out.println("\n--- Semua Karakter ---");
        for (Character character : manager.getAllCharacters()) {
            System.out.println(character);
        }

        System.out.println("\n--- Pencarian ---");
        System.out.println("Cari Luna: " + manager.findByName("Luna"));
        System.out.println("Cari Bima: " + manager.findByName("Bima"));

        System.out.println("\n--- Statistik ---");
        System.out.println("Total karakter: " + manager.getTotalCharacters());
        System.out.println("Rata-rata level: " + manager.getAverageLevel());
        System.out.println("Jumlah Warrior: "
                + manager.countByType(CharacterType.WARRIOR));

        System.out.println("\n--- Cari Berdasarkan Tipe ---");
        for (Character character : manager.findByType(CharacterType.MAGE)) {
            System.out.println(character);
        }

        System.out.println("\n--- Uji Defensive Copy ---");
        // TAMBAHAN: tampilkan ukuran SEBELUM dan SESUDAH supaya buktinya jelas
        ArrayList<Character> salinan = manager.getAllCharacters();
        System.out.println("Ukuran salinan sebelum clear: " + salinan.size());
        salinan.clear();
        System.out.println("Ukuran salinan setelah clear: " + salinan.size());
        System.out.println("Total setelah salinan dibersihkan: "
                + manager.getTotalCharacters());

        System.out.println("\n--- Hapus Karakter ---");
        System.out.println("Hapus Luna: " + manager.removeCharacter("Luna"));
        System.out.println("Total setelah hapus: " + manager.getTotalCharacters());

        System.out.println("\n--- Uji Rata-rata Daftar Kosong ---");
        manager.removeCharacter("Aria");
        manager.removeCharacter("Raven");
        System.out.println("Rata-rata saat kosong: " + manager.getAverageLevel());

        // TAMBAHAN: eksperimen reference (soal D.1)
        System.out.println("\n--- Eksperimen Reference ---");
        Character a = new Character("Hero", CharacterType.WARRIOR);
        Character b = a;
        b.setLevel(8);
        System.out.println("a.getLevel() = " + a.getLevel());
        System.out.println("a == b = " + (a == b));

        Character c = new Character(a.getName(), a.getType(), a.getLevel());
        c.setLevel(2);
        System.out.println("Salinan independen: a = " + a.getLevel()
                + ", c = " + c.getLevel());
        System.out.println("a == c = " + (a == c));
        System.out.println("a.equals(c) = " + a.equals(c));

        // TAMBAHAN: static counter (soal D.3)
        System.out.println("\n--- Static Counter ---");
        System.out.println("Total Character pernah dibuat: "
                + Character.getTotalCharacterCreated());
        System.out.println("Total di manager sekarang: "
                + manager.getTotalCharacters());
    }
}