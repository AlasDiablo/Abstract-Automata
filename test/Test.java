import fr.alasdiablo.automata.link.IBasicLink;
import fr.alasdiablo.automata.state.BasicState;

import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        BasicState<String, Character> stateNumberDectector = new StateNumberDectector();
        BasicState<String, Character> stateNumberPrinter = new StateNumberPrinter();
        IBasicLink<Character> linkNumber = new LinkNumber();
        IBasicLink<Character> linkCaracter = new LinkCharacter();
        String str = "kjffgjds dgd5gdfg 5s4 fgqdg88 gqg6465 d465 d \n f465s 6fqqdf 4";
        stateNumberDectector.addOutput(stateNumberDectector, linkCaracter);
        stateNumberDectector.addOutput(stateNumberPrinter, linkNumber);
        stateNumberPrinter.addOutput(stateNumberDectector, linkCaracter);
        stateNumberPrinter.addOutput(stateNumberPrinter, linkNumber);
        stateNumberDectector.callAndExecute(str);
    }
}

class LinkNumber implements IBasicLink<Character> {
    @Override
    public boolean checkIfValid(Character value) {
        switch (value) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            return true;
            default:
                return false;
        }
    }
}

class LinkCharacter implements IBasicLink<Character> {
    @Override
    public boolean checkIfValid(Character value) {
        switch (value) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return false;
            default:
                return true;
        }
    }
}

class StateNumberDectector extends BasicState<String, Character> {
    @Override
    public Character dataToLink(String data) {
        try {
            char c = data.substring(0, 1).toCharArray()[0];
            this.setData(Objects.requireNonNull(this.getData()).substring(1, this.getData().length()));
            return c;
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public void actionOnCall(Character data) {}
}

class StateNumberPrinter extends  BasicState<String, Character> {

    @Override
    public Character dataToLink(String data) {
        try {
            char c = data.substring(0, 1).toCharArray()[0];
            this.setData(Objects.requireNonNull(this.getData()).substring(1, this.getData().length()));
            return c;
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public void actionOnCall(Character data) {
        System.out.print(data.charValue());
    }
}