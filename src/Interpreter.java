import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by natasha on 2016-08-13.
 */
public class Interpreter {

    private static Machine machine;
    private static final String HELLO_WORLD = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
    private static final String FIBONACCI = "+++++++++++\n" +
            ">+>>>>++++++++++++++++++++++++++++++++++++++++++++\n" +
            ">++++++++++++++++++++++++++++++++<<<<<<[>[>>>>>>+>\n" +
            "+<<<<<<<-]>>>>>>>[<<<<<<<+>>>>>>>-]<[>++++++++++[-\n" +
            "<-[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]>[<<[>>>+<<<\n" +
            "-]>>[-]]<<]>>>[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]\n" +
            ">[<<+>>[-]]<<<<<<<]>>>>>[+++++++++++++++++++++++++\n" +
            "+++++++++++++++++++++++.[-]]++++++++++<[->-<]>++++\n" +
            "++++++++++++++++++++++++++++++++++++++++++++.[-]<<\n" +
            "<<<<<<<<<<[>>>+>+<<<<-]>>>>[<<<<+>>>>-]<-[>>.>.<<<\n" +
            "[-]]<<[>>+>+<<<-]>>>[<<<+>>>-]<<[<+>-]>[<+>-]<<<-]";

    private static String program = FIBONACCI;

    public static void main(String[] args) {

        machine = new Machine();

        int pc = 0;
        ArrayList<Integer> jumpStack = new ArrayList<Integer>();
        while (pc < program.length()) {
            char instruction = program.charAt(pc);

            if (instruction == '[') {
                if (!machine.hasMemory()) {

                    int openCount = 0;
                    for (int i = pc+1; i < program.length(); i++) {
                        if (program.charAt(i) == '[') openCount++;
                        else if (program.charAt(i) == ']') {
                            if (openCount == 0) {
                                pc = i;
                                break;
                            } else {
                                openCount--;
                            }
                        }
                    }
                } else {
                    jumpStack.add(0, pc);
                }
            }

            else if (instruction == ']') {
                pc = jumpStack.remove(0)-1;
            }

            else {
                doThing(instruction);
            }
            pc++;
        }

    }

    private static void doThing(char instruction) {
        Scanner scan = new Scanner(System.in);
        switch (instruction) {
            case '>':
                machine.incrementPointer();
                break;
            case '<':
                machine.decrementPointer();
                break;
            case '+':
                machine.addValue();
                break;
            case '-':
                machine.subtractValue();
                break;
            case '.':
                System.out.print(machine.getValue());
                break;
            case ',':
                String b = scan.next(".");
                machine.setValue((byte) b.toCharArray()[0]);
                break;
            default:
                break;
        }
    }
}
