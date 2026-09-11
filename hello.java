// As I understand from the syllabus, we will be instructed when we will use BNF grammar.
// This assignment we get to choose any language so it will be written in Java
// Source from geeksforgeeks
// https://www.geeksforgeeks.org/java/brainfuck-interpreter-java/

import java.util.Scanner;

class BrainFuck
{
    private static final Scanner ob = new Scanner(System.in);
    private static int ptr; // Data pointer

    // Max memory limit. Unsigned 8-bit binary
    // https://thinkmatrix.blog/maximum-number-for-a-byte
    private static final int length = 255;

    // 256 bits from 0 to 255.
    private static final byte[] memory = new byte[length];

    // Interpreter function which accepts the code a string parameter
    private static void interpret(String s)
    {
        int c = 0;

        // Parsing through each character of the code
        for (int i = 0; i < s.length(); i++)
        {
            // > moves the pointer to the right
            if (s.charAt(i) == '>')
            {
                if (ptr == length - 1)//If memory is full
                    ptr = 0;//pointer is returned to zero
                else
                    ptr ++;
            }

            // < moves the pointer to the left
            else if (s.charAt(i) == '<')
            {
                if (ptr == 0) // If the pointer reaches zero

                    // pointer is returned to rightmost memory position
                    ptr = length - 1;
                else
                    ptr --;
            }

            // + increments the value of the memory cell under the pointer
            else if (s.charAt(i) == '+')
                memory[ptr] ++;

                // - decrements the value of the memory cell under the pointer
            else if (s.charAt(i) == '-')
                memory[ptr] --;

                // . outputs the character signified by the cell at the pointer
            else if (s.charAt(i) == '.')
                System.out.print((char)(memory[ptr]));

                // , inputs a character and store it in the cell at the pointer
            else if (s.charAt(i) == ',')
                memory[ptr] = (byte)(ob.next().charAt(0));

                // [ jumps past the matching ] if the cell under the pointer is 0
            else if (s.charAt(i) == '[')
            {
                if (memory[ptr] == 0)
                {
                    i++;
                    while (c > 0 || s.charAt(i) != ']')
                    {
                        if (s.charAt(i) == '[')
                            c++;
                        else if (s.charAt(i) == ']')
                            c--;
                        i ++;
                    }
                }
            }

            // ] jumps back to the matching [ if the cell under the pointer is nonzero
            else if (s.charAt(i) == ']')
            {
                if (memory[ptr] != 0)
                {
                    i --;
                    while (c > 0 || s.charAt(i) != '[')
                    {
                        if (s.charAt(i) == ']')
                            c ++;
                        else if (s.charAt(i) == '[')
                            c --;
                        i --;
                    }
                }
            }
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        System.out.println("Enter the code:");
        String code = ob.nextLine();
        System.out.println("Output:");
        interpret(code);
    }
}