package Exception;

import Core.SaveFileCache;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveStateException extends Exception{

    public static void save(SaveFileCache data) throws IOException{
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Cache/Credentials.txt"));
            out.writeObject(data);
        }
        finally {
            out.close();
            System.out.println("Saved User State");
            System.exit(0);
        }
    }
}
