import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class replace {
    public static void main(String[] args) {
        try {
            String valuePath = "/Users/<user-name>/<app-name>/<app-yaml>";
            String values = new String(Files.readAllBytes(Paths.get(valuesPath)));

            String envFilePath = "Users/<user-name>/<app-name>/<app-env>";
            String env = new String(Files.readAllBytes(Paths.get(envFilePath)));

            String[] envLines = env.split(regex: "\n");

            // Replace these items
            if(values.contains("<item 1>") && !values.contains("<item 2>"))
                values = values+"<new item>";
            if(values.contains("<item 3>") && !values.contains("<item 4>"))
                values = values+"<new item>";
            for (String line : envLines) {
                String[] variable = line.split(regex: "=", limit:2);
                if(values.contains("${"+variable[0]+"}"))
                    values=values.replace("${"+variable[0]+"}", variable[1]);
            }
            String[] valuesLines = values.split(regex:"\n");

            // Remove these items
            for(String line : valuesLines) { 
                if (line.contains("<item 5") && !line.contains("<item 6>"))
                    values = values.replace(line, "");
                if (line.contains("<item 7>") && values.contains("<item 8>"))
                    values = values.replace(line, "");
                if (line.contains("<item 9>"))
                    values = values.replace(line, "");
            }
            System.out.println(values);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}