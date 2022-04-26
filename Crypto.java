// The "Crypto" class.
import java.awt.*;
import hsa.Console;
import java.io.*;

public class Crypto
{
    static Console c;      // The output console

    public static void main (String[] args) throws IOException
    {
	c = new Console ();
	String fileName;
	String key = "ics4u";
	c.println ("Text file to encrypt?");
	fileName = c.readString ();
	Vigenere encrypted = new Vigenere (key, fileName);
	c.println ("Done");
	// Place your program here. 'c' is the output console
    } // main method
} // Crypto class


class Vigenere
{
    String key;

    public Vigenere ()
    {
	this.key = "ics4u";
    }


    public Vigenere (String inputKey, String fileName) throws IOException
    {
	this.key = inputKey;
	encrypt (fileName);
	decrypt (fileName);
    }


    public void encrypt (String fileName) throws IOException
    {
	BufferedReader input = new BufferedReader (new FileReader (fileName + ".txt"));
	String line, encryptedLine;
	PrintWriter output;
	output = new PrintWriter (new FileWriter (fileName + ".cyp"));
	line = "";
	while (line != null)
	{
	    line = input.readLine ();
	    if (line != null)
	    {
		encryptedLine = encryptLine (line);
		output.println (encryptedLine);
	    }
	}
	output.close ();
    }


    public String encryptLine (String line)
    {
	int offset;
	int result;
	String encrypted = "";
	for (int i = 0 ; i < line.length () ; i++)
	{
	    offset = this.key.charAt (i % this.key.length ()) - 32;
	    result = line.charAt (i) + offset;
	    if (result > 126)
	    {
		result = result + (-126 + 31);
	    }
	    encrypted = encrypted + (char) result;
	}
	return encrypted;
    }


    public void decrypt (String fileName) throws IOException
    {
	BufferedReader input = new BufferedReader (new FileReader (fileName + ".cyp"));
	String line, decryptedLine;
	PrintWriter output;
	output = new PrintWriter (new FileWriter (fileName + ".pln"));
	line = "";
	while (line != null)
	{
	    line = input.readLine ();
	    if (line != null)
	    {
		decryptedLine = decryptLine (line);
		output.println (decryptedLine);
	    }
	}
	output.close ();
    }


    public String decryptLine (String line)
    {
	int offset;
	int result;
	String decrypted = "";
	for (int i = 0 ; i < line.length () ; i++)
	{
	    offset = this.key.charAt (i % this.key.length ()) - 32;
	    result = line.charAt (i) - offset;
	    if (result < 32)
	    {
		result = result + (126 - 31);
	    }
	    decrypted = decrypted + (char) result;
	}
	return decrypted;
    }
}
