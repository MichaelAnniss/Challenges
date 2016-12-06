package me.mikey.challenges.week8.commands;

import me.mikey.challenges.week8.exceptions.InvalidCommandException;
import me.mikey.challenges.week8.items.WeaponItem;
import me.mikey.challenges.week8.models.Monster;
import me.mikey.challenges.week8.models.Player;

import java.util.List;

/**
 * Created by Michael on 06/12/2016.
 */
public class FightCommand extends Command {
    @Override
    public void execute(String command, List<String> args) throws InvalidCommandException {
        Player player = Player.getInstance();
        WeaponItem weaponItem = player.getCurrentWeapon();

        if(player.getCurrentRoom().getMonsters().size() == 0) {
            throw new InvalidCommandException("There are no monsters to fight!");
        }

        // Damage all Monsters in the room
        weaponItem.use();

        // Damage the player
        player.damage(player.getCurrentRoom().getMonsters().stream().mapToInt(Monster::getHealth).sum());
    }
}
