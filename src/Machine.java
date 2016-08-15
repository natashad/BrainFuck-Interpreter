import java.util.Arrays;

/**
 * Created by natasha on 2016-08-13.
 */
public class Machine {

    private static final int memorySize = 10000;

    private byte[] memory;
    private int pointer;

    public Machine() {
        this.memory = new byte[memorySize];
        Arrays.fill(memory, (byte)0);
        this.pointer = 0;

    }

    public void incrementPointer() {
        if(pointer >= memorySize) {
            System.err.println("Out of bounds");
            return;
        }
        this.pointer++;
    }

    public void decrementPointer() {
        if (pointer == 0) {
            System.err.println("Out of bounds");
            return;
        }
        this.pointer--;
    }

    public void addValue() {
        memory[pointer]++;
    }

    public void subtractValue() {
        memory[pointer]--;
    }

    public char getValue() {
        return (char)memory[pointer];
    }

    public void setValue(byte value) {
        memory[pointer] =  value;
    }

    public boolean isOutOfBounds() {
        if (pointer < 0 || pointer >= memorySize) {
            return true;
        }
        return false;
    }

    public boolean hasMemory() {
        return (memory[pointer] != (byte)0);
    }

}
