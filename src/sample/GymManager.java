package sample;

import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

public interface GymManager {
    public void addMember(DefaultMember member) throws IOException;

    public boolean deleteMember(int membershipNumber);

    public void printListOfMember();

    public List<DefaultMember> sortMember();

    public void saveMembers();

    public void search(String searchValue, Label label1);
}