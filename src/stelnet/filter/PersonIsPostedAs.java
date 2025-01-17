package stelnet.filter;

import com.fs.starfarer.api.characters.PersonAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.SettingsUtils;

@RequiredArgsConstructor
public class PersonIsPostedAs extends PersonFilter {

    private final String postId;

    @Override
    protected boolean acceptPerson(PersonAPI person) {
        return person.getPostId().equals(postId);
    }

    @Override
    public String toString() {
        PersonAPI person = SettingsUtils.createPerson();
        person.setPostId(postId);
        return person.getPost();
    }
}
