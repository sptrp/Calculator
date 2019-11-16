import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class CountController {

    // Method which makes postfix from the input infix string
    public static String makePostfix(String input) {

        // Create a number container and a operator buffer
        StringBuilder vault = new StringBuilder();
        Stack<Character> buffer = new Stack<>();

        // Remove unused signs at the beginning
        input = input.replaceAll("^--|^[\\/\\*\\+]", "");
        // Mark minus as negative sign
        input = input.replaceAll("^-|(?<=[\\-\\/\\*\\+])[-]", "n");



        for (int i = 0; i<input.length(); i++) {

            Character pointer = input.charAt(i);

            if (pointer=='n' || pointer=='.') {
                vault.append(pointer);
                continue;
            }

            // If operator
            if (!Character.isDigit(pointer)) {

                // If not empty buffer and precedence of operator is higher in buffer
                if (!buffer.isEmpty() && isHigerPrec(pointer, buffer.peek())) {

                    // Append operator from the buffer
                    vault.append(' ').append(buffer.pop()).append(' ');
                    buffer.push(pointer);

                    // If empty buffer
                } else {

                    vault.append(' ');
                    buffer.push(pointer);
                }

                // If digit
            } else if (Character.isDigit(pointer) || pointer=='.') { vault.append(pointer); }

        }
        // Append last operator
        while (!buffer.isEmpty()) {
            vault.append(' ').append(buffer.pop()).append(' ');
        }

        return vault.toString();
    }

    // Method which evaluate the normalized string input
    static double evaluatePostfix(String exp) {

        Stack<Double> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(exp, " ");
        String regexDig = "[0-9, .]+";

        // iterate through tokens
        while(st.hasMoreElements()) {

            String elem = st.nextToken();

            // if n, negative the number and push to the stack
            if (elem.contains("n")) {

                elem = elem.substring(1, elem.length());
                Double dig = -(Double.parseDouble(elem));
                stack.push(dig);
                elem = st.nextToken();
            }

            // if number
            if(elem.matches(regexDig)) {

                Double dig = Double.parseDouble(elem);
                stack.push(dig);

            // if sign
            } else {

                Double val1 = stack.pop();
                Double val2 = stack.pop();

                switch(elem) {

                    case "+":
                        stack.push(val2+val1);
                        break;

                    case "-":
                        stack.push(val2- val1);
                        break;

                    case "/":
                        stack.push(val2/val1);
                        break;

                    case "*":
                        stack.push(val2*val1);
                        break;
                }
            }
        }
        return stack.pop();
    }

    // Enum to store operator precedence
    private enum Operator {

        ADD(1), SUBTRACT(2), MULTIPLY(3), DIVIDE(3);
        final int precedence;
        Operator(int p) { precedence = p; }
    }

    // Map to order operators to its precedence
    private static Map<Character, CountController.Operator> ops = new HashMap<Character, Operator>() {{

        put('+', CountController.Operator.ADD);
        put('-', CountController.Operator.SUBTRACT);
        put('*', CountController.Operator.MULTIPLY);
        put('/', CountController.Operator.DIVIDE);
    }};

    // Method to check if the precedence of operator is higher as in the buffer
    private static boolean isHigerPrec(Character op, Character sub) {

        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }
}
