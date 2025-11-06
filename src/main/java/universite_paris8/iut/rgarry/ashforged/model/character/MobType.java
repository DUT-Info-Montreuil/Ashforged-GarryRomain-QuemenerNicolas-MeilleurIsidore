package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Item.Weapon;

public enum MobType {
    MONGOLFIERE("Mongolfière", 5, 45, 28, Weapon.wooden_sabre),
    SOLDAT("Soldat", 6, 55, 30, Weapon.stone_sword),
    ZOMBIE("Zombie", 4, 70, 24, Weapon.stick),
    BANDIT("Bandit", 7, 50, 35, Weapon.firearm),
    BOSS("Boss", 5, 200, 60, Weapon.enma),
    KOZUKI("Kozuki", 6, 65, 40, Weapon.iron_sword);

    public final String displayName;
    public final int baseSpeed;
    public final int baseHealth;
    public final int baseForce;
    public final Weapon defaultWeapon;

    MobType(String displayName, int baseSpeed, int baseHealth, int baseForce, Weapon defaultWeapon) {
        this.displayName = displayName;
        this.baseSpeed = baseSpeed;
        this.baseHealth = baseHealth;
        this.baseForce = baseForce;
        this.defaultWeapon = defaultWeapon;
    }
}
