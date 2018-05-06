package teamgarbo.github.io.eyeoftyche.Engine.PlayerProperties;

import java.util.ArrayList;

import teamgarbo.github.io.eyeoftyche.Engine.WorldObjects.Spell;

/**
 * Created by hercu on 05-May-18.
 */

public class SpellBook {

    ArrayList<Spell> spellBook = new ArrayList<>();

    public void addSpell(Spell spell)
    {
        spellBook.add(spell);
    }

    public ArrayList<Spell> getSpellBook()
    {
        return spellBook;
    }
}
