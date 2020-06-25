package fr.mimich.wishinjector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public final class Main {

    public static Boolean FOUNDED;

    static {

        FOUNDED = false;

    }

    public static void main(String[] args) {

        if (args.length <= 1) {

            System.out.println("Usage: java -jar WishInjector.jar <modpath> <mod folder instance>");

            return;

        }

        final String modPath = args[0];

        final String modDirInstancePath = args[1];

        final File modFile = new File(modPath);

        final File modDirInstanceFile = new File(modDirInstancePath);

        final File agent = new File(modDirInstanceFile, "agent");

        try {

            if (agent.createNewFile()) {

                System.out.println("Agent was injected");

            } else {

                return;

            }

        } catch (IOException e) {

            e.printStackTrace();

            return;

        }

        System.out.println("Injection...");

        while (true) {

            if (!agent.exists()) {

                System.out.println("Agent was deleted");

                try (FileInputStream fis = new FileInputStream(modFile);
                   FileOutputStream fos = new FileOutputStream(new File(modDirInstanceFile, modFile.getName()))) {
                    int len;
                    byte[] buffer = new byte[4096];
                    while ((len = fis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }

                    fos.flush();

                    fos.close();

                    System.out.println("Injected");

                    return;

                } catch (IOException e) {

                    e.printStackTrace();

                    return;
                }


            }

        }

    }

}
