package universite_paris8.iut.rgarry.ashforged.model.ia;

import java.util.List;
import universite_paris8.iut.rgarry.ashforged.model.BFS;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Position;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.model.character.Ennemis;

public class ChaseWithBFSStrategy implements MovementStrategy {
    private static final int VISION_RANGE = 5;
    private static final int JUMP_STRENGTH = -12;

    @Override
    public void move(Ennemis mob, Environment env) {
        Character hero = env.getHero();

        int mobX = mob.getX() / 64;
        int mobY = mob.getY() / 64;
        int heroX = hero.getX() / 64;
        int heroY = hero.getY() / 64;

        // Si le héros est loin, marche aléatoire
        if (Math.abs(heroX - mobX) > VISION_RANGE) {
            mob.seDeplacerRandom();
            return;
        }

        // BFS vers le joueur
        BFS bfs = new BFS(env.getField());
        List<Position> path = bfs.findPath(new Position(mobX, mobY), new Position(heroX, heroY));

        if (path.size() <= 1) {
            mob.seDeplacerRandom();
            return;
        }

        Position next = path.get(1);

        if (next.x < mobX) mob.vaAGauche();
        else if (next.x > mobX) mob.vaADroite();

        // Saut si nécessaire (case au-dessus)
        if (next.y < mobY) {
            boolean onGroundLeft = env.checkCollision(mob.getX(), mob.getY() + 32);
            boolean onGroundRight = env.checkCollision(mob.getX() + 31, mob.getY() + 32);
            if (onGroundLeft && onGroundRight) {
                mob.setVelocityY(JUMP_STRENGTH);
            }
        }
    }
}
