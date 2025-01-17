package stelnet.filter;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.SettingsUtils;

@RequiredArgsConstructor
public class PersonHasSkill extends PersonOfficerFilter {

    private final String skillId;

    @Override
    public boolean acceptPerson(PersonAPI person) {
        if (super.acceptPerson(person)) {
            return true;
        }
        MutableCharacterStatsAPI characterStats = person.getStats();
        float skillLevel = characterStats.getSkillLevel(skillId);
        return skillLevel > 0;
    }

    @Override
    public String toString() {
        SkillSpecAPI skill = SettingsUtils.getSkill(skillId);
        return skill.getName();
    }
}
