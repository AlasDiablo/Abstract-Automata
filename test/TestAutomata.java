import fr.alasdiablo.automata.link.IBasicLink;
import fr.alasdiablo.automata.state.BasicState;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class TestAutomata {

    public static String test_number_detector_out = "";

    @Test
    public void test_number_detector() {
        // create number detector
        BasicState<String, Character> stateNumberDectector = new StateNumberDectector();
        // create number printer
        BasicState<String, Character> stateNumberPrinter = new StateNumberPrinter();
        // create link to the number printer
        IBasicLink<Character> linkNumber = new LinkNumber();
        // create link to number detector
        IBasicLink<Character> linkCaracter = new LinkCharacter();
        // add link to state (from number detector to number detector)
        stateNumberDectector.addOutput(stateNumberDectector, linkCaracter);
        // add link to state (from number detector to number printer)
        stateNumberDectector.addOutput(stateNumberPrinter, linkNumber);
        // add link to state (from number printer to number detector)
        stateNumberPrinter.addOutput(stateNumberDectector, linkCaracter);
        // add link to state (from number printer to number printer)
        stateNumberPrinter.addOutput(stateNumberPrinter, linkNumber);

        // String to test
        //                   1      2     34                 5              6           7           8                       9
        String input = "HDOCJ1VOBpae2zrkjb34pfpBKLDSFGQPOIpoe5kjhfdkgksdfgùù6qgkgfqroeç_7'DFFKLKGFDS8GlfjdgsdhfarhtfjhgKJHFD9GKSJDFLKGJlkfdgslsdg";
        // list of number in the string
        String number = "123456789";
        // execute the number detector
        stateNumberDectector.callAndExecute(input);

        // test if the detector work correctly
        Assert.assertEquals("This output is wrong", number, test_number_detector_out);
    }


}
// test_number_detector class declaration
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
        TestAutomata.test_number_detector_out += data;
    }
}