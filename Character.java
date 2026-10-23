package com.unpri.charactermanager.model;

import java.util.Objects;

public class Character {
    // TAMBAHAN: counter static, milik class (dipakai bersama semua objek)
    private static int totalCharacterCreated = 0;

    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    // Constructor 1: karakter baru, otomatis level 1
    public Character(String name, CharacterType type) {
        this(name, type, 1);
    }

    // Constructor 2: karakter dengan level tertentu
    public Character(String name, CharacterType type, int level) {
        this.name = name;
        this.type = type;
        setLevel(level);
        totalCharacterCreated++; // TAMBAHAN: naik setiap objek berhasil dibuat
    }

    private void recalculateStats() {
        int baseHp;
        int baseAttack;

        switch (type) {
            case WARRIOR:
                baseHp = 120;
                baseAttack = 15;
                break;
            case MAGE:
                baseHp = 80;
                baseAttack = 20;
                break;
            case ARCHER:
                baseHp = 90;
                baseAttack = 18;
                break;
            case ROGUE:
                baseHp = 85;
                baseAttack = 17;
                break;
            default:
                throw new IllegalStateException(
                    "Tipe tidak dikenal: " + type
                );
        }

        this.hitPoints = baseHp + (level - 1) * 20;
        this.attackPower = baseAttack + (level - 1) * 3;
    }

    public void levelUp() {
        setLevel(level + 1);
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    // TAMBAHAN: getter static, dipanggil lewat nama class:
    // Character.getTotalCharacterCreated()
    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        this.type = type;
        recalculateStats();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException(
                "Level minimal 1"
            );
        }

        this.level = level;
        recalculateStats();
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        if (hitPoints < 0) {
            throw new IllegalArgumentException(
                "HP tidak boleh negatif"
            );
        }
        this.hitPoints = hitPoints;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        if (attackPower < 0) {
            throw new IllegalArgumentException(
                "Attack tidak boleh negatif"
            );
        }
        this.attackPower = attackPower;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException(
                "Mana tidak boleh negatif"
            );
        }
        this.mana = mana;
    }

    // TP5: dua karakter dianggap sama jika namanya sama
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Character)) {
            return false;
        }

        Character other = (Character) obj;

        return Objects.equals(this.name, other.name);
    }

    // TP5: hashCode harus konsisten dengan equals
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Character{name='" + name + "', type=" + type
                + ", level=" + level + ", hp=" + hitPoints
                + ", attack=" + attackPower + ", mana=" + mana + "}";
    }
}