package universite_paris8.iut.rgarry.ashforged.model.factory;

import java.util.Random;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.ia.ChaseWithBFSStrategy;
import universite_paris8.iut.rgarry.ashforged.model.ia.MovementStrategy;
import universite_paris8.iut.rgarry.ashforged.model.ia.RandomWalkStrategy;
import universite_paris8.iut.rgarry.ashforged.model.character.Ennemis;
import universite_paris8.iut.rgarry.ashforged.model.character.MobType;

public class MobFactory {
    private static final Random RAND = new Random();

    public Ennemis create(MobType type, Environment env, MovementStrategy strategy) {
        int[] spawn = findSpawn(env);
        int x = spawn[0];
        int y = spawn[1];

        Ennemis mob = new Ennemis(
                type.displayName,
                x, y,
                type.baseSpeed,
                type.baseHealth,
                type.baseForce,
                'g',
                type.defaultWeapon,
                0
        );

        mob.setHoldingItem(type.defaultWeapon);
        mob.setMovementStrategy(strategy != null ? strategy : defaultStrategyFor(type));
        return mob;
    }

    public Ennemis create(MobType type, Environment env) {
        return create(type, env, defaultStrategyFor(type));
    }

    public Ennemis createRandom(Environment env) {
        MobType[] values = MobType.values();
        MobType randomType = values[RAND.nextInt(values.length)];
        return create(randomType, env);
    }

    private MovementStrategy defaultStrategyFor(MobType type) {
        switch (type) {
            case BOSS:
            case KOZUKI:
                return new ChaseWithBFSStrategy();
            default:
                return new RandomWalkStrategy();
        }
    }

    private int[] findSpawn(Environment env) {
        int widthPx = env.getField().getWidth() * 64;
        int x, y;
        int attempts = 0;

        do {
            x = RAND.nextInt(widthPx);
            y = 300;
            attempts++;
            if (attempts > 1000) break;
        } while (env.checkCollision(x, y));

        return new int[]{x, y};
    }
}
