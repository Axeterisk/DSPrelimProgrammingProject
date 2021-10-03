import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.stream.Stream;

public class Menu {

    private static String currentString;
    private static String oldString;
    private static String copiedString;
    private static String fileName = "./";
    private static int slideCounter = 0;
    private static int fontSize = 12;
    private static int slideSize = 10;
    private static final String[] authors = {
            "ANCHETA, Charles Jr.",
            "BUSTARDE, Jerome",
            "CASTRO, Enrico",
            "GARRIDO, Lupin",
            "NUDO, Kurt Matthew"
    };

    private static String getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    private static void workspace() {
        currentString = getInput("Input to slide");
    }

    private static MenuItem<MenuItem<String>>
    navigateRibbon(MenuItem<MenuItem<MenuItem<String>>> ribbon) {
        Node<MenuItem<MenuItem<String>>> current = ribbon.getHead();
        StringBuilder menu = new StringBuilder("[ ");
        String selection;
        boolean isInvalid = true;

        System.out.println(ribbon.getLabel());
        do {
            menu.append(current.getData().getLabel());
            if (current.getNext() != null) menu.append(", ");
            current = current.getNext();
        } while (current != null);
        menu.append(", Workspace ]");
        System.out.println(menu);

        // return the element at the index

        while (true) {
            selection = getInput("Selection");
            current = ribbon.getHead();
            while (current != null) {
                if (current.getData().getLabel().equalsIgnoreCase(selection)) {
                    isInvalid = false;
                    break;
                } else current = current.getNext();
            }
            if (!isInvalid || selection.equalsIgnoreCase("workspace")) break;
            System.out.println("Invalid Input.");
        }

        if (selection.equalsIgnoreCase("workspace")) {
            workspace();
            return ribbon.getHead().getData();
        }

        return current.getData();
    }

    private static MenuItem<String>
    navigateTabs(MenuItem<MenuItem<String>> tab) {
        Node<MenuItem<String>> current = tab.getHead();
        StringBuilder menu = new StringBuilder("[ ");
        String selection;
        boolean isInvalid = true;

        System.out.println(tab.getLabel());
        do {
            menu.append(current.getData().getLabel());
            if (current.getNext() != null) menu.append(", ");
            current = current.getNext();
        } while (current != null);
        menu.append(" ]");
        System.out.println(menu);

        // reset pointer to current (index 0)

        while (true) {
            selection = getInput("Selection");
            current = tab.getHead();
            while (current != null) {
                if (current.getData().getLabel().equalsIgnoreCase(selection)) {
                    isInvalid = false;
                    break;
                } else current = current.getNext();
            }
            if (!isInvalid) break;
            System.out.println("Invalid Input.");
        }

        return current.getData();
    }

    private static String
    navigateSubmenu(MenuItem<String> submenu) {
        Node<String> current = submenu.getHead();
        StringBuilder menu = new StringBuilder("[ ");
        String selection;
        boolean isInvalid = true;

        System.out.println(submenu.getLabel());
        do {
            menu.append(current.getData());
            if (current.getNext() != null) menu.append(", ");
            current = current.getNext();
        } while (current != null);
        menu.append(" ]");
        System.out.println(menu);

        // reset pointer to current (index 0)
        current = submenu.getHead();

        while (true) {
            selection = getInput("Selection");
            current = submenu.getHead();
            while (current != null) {
                if (current.getData().equalsIgnoreCase(selection)) {
                    isInvalid = false;
                    break;
                } else current = current.getNext();
            }
            if (!isInvalid) break;
            System.out.println("Invalid Input.");
        }

        return current.getData();
    }

    private static void stringHandler(String string) {
        boolean autosave = true;

        /*
            TAB FILE
         */
        // FILE/QUICK OPERATIONS
        if (string.equalsIgnoreCase("autosave")) {
            autosave ^= true;
            System.out.println("Autosave: " + autosave);
            return;
        }

        if (string.equalsIgnoreCase("save")) {
            try {
                Files.writeString(Paths.get(fileName), currentString);
            } catch (IOException e) {
                System.out.println("I/O Error.");
            }
            return;
        }

        if (string.equalsIgnoreCase("undo")) {
            currentString = new String(oldString);
            oldString = "";
            System.out.println("Unloaded String in buffer.");
            return;
        }

        if (string.equalsIgnoreCase("redo")) {
            currentString += ('\n' + currentString);
            System.out.println(currentString);
            return;
        }

        if (string.equalsIgnoreCase("present")) {
            System.out.println("Presenting Screen");
            return;
        }

        // FILE/FILE OPERATIONS
        if (string.equalsIgnoreCase("new")) {
            String emptyString = "";
            String path = "./";
            path += getInput("File name");
            try {
                Files.writeString(Paths.get(path), emptyString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Creating a new file.");
            return;
        }

        if (string.equalsIgnoreCase("open")) {
            String path = "./";
            path += getInput("File name");
            try (Stream<String> stream = Files.lines(Paths.get(path))) {
                stream.forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("File not found.");
            }
            return;
        }

        if (string.equalsIgnoreCase("info")) {
            System.out.println("Microsoft Powerpoint: Menu Modelling");
            System.out.println("Working file: " + fileName);
            System.out.println("Operating System: " + System.getProperty("os.name"));
            System.out.println("Java Environment: " + System.getProperty("java.version"));
            return;
        }

        if (string.equalsIgnoreCase("save as")) {
            String emptyString = "";
            String path = "./";
            path += getInput("File name");
            try {
                Files.writeString(Paths.get(path), emptyString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Creating a new file.");
            return;
        }

        if (string.equalsIgnoreCase("print")) {
            System.out.println("Printing " + fileName);
            return;
        }

        if (string.equalsIgnoreCase("share")) {
            System.out.println("Sharing " + fileName);
            return;
        }

        if (string.equalsIgnoreCase("export")) {
            System.out.println("Exporting " + fileName + " as .pdf.");
            return;
        }

        if (string.equalsIgnoreCase("close")) {
            System.exit(0);
        }

        // FILE/OTHERS
        if (string.equalsIgnoreCase("account")) {
            System.out.println("Microsoft Powerpoint");
            System.out.println("Product Status: Activated");
            System.out.println("Licensed to: " + System.getProperty("user.name"));
            return;
        }

        if (string.equalsIgnoreCase("feedback")) {
            String concerns = getInput("Input your concerns: ");
            try {
                Files.writeString(Paths.get("./Feedback.txt"), concerns);
            } catch (IOException e) {
                System.out.println("I/O Error.");
            }
            System.out.println("Response documented.");
            return;
        }

        if (string.equalsIgnoreCase("options")) {
            System.out.println("Options are set to read-only.");
            System.out.println("Contact your domain administrator.");
            return;
        }

        /*
            TAB HOME
         */

        // HOME/CLIPBOARD
        if (string.equalsIgnoreCase("paste")) {
            try {
                Files.writeString(Paths.get(fileName), copiedString,
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Creating a new file.");
            return;
        }

        if (string.equalsIgnoreCase("copy")) {
            copiedString = new String(currentString);
            System.out.println(copiedString + " copied.");
            return;
        }

        if (string.equalsIgnoreCase("format painter")) {
            System.out.println("Format painter is disabled.");
            return;
        }

        // HOME/SLIDES

        if (string.equalsIgnoreCase("new slide")) {
            System.out.println("Slides: " + ++slideCounter);
            return;
        }

        if (string.equalsIgnoreCase("layout")) {
            System.out.println("No layouts preconfigured.");
            return;
        }

        if (string.equalsIgnoreCase("reset")) {
            System.out.println("Refreshing current state.");
            System.out.println("Terminating program.");
            System.exit(0);
        }

        // HOME/FONT
        if (string.equalsIgnoreCase("font selector")) {
            StringBuilder menu = new StringBuilder("[ ");
            String selection;
            boolean isInvalid = true;
            MySinglyLinkedList<String> fonts = new MySinglyLinkedList<>();
            Node<String> current = fonts.getHead();

            fonts.insert("Arial");
            fonts.insert("Calibri");
            fonts.insert("Times New Roman");

            do {
                menu.append(current.getData());
                if (current.getNext() != null) menu.append(", ");
                current = current.getNext();
            } while (current != null);
            menu.append(" ]");
            System.out.println(menu);

            while (true) {
                selection = getInput("Selection");
                current = fonts.getHead();
                while (current != null) {
                    if (current.getData().equalsIgnoreCase(selection)) {
                        isInvalid = false;
                        break;
                    } else current = current.getNext();
                }
                if (!isInvalid) break;
                System.out.println("Invalid Input.");
            }
            System.out.println(current.getData() + " applied to text.");
            return;
        }

        if (string.equalsIgnoreCase("increase font size")) {
            System.out.println("Font size: " + ++fontSize);
            return;
        }

        if (string.equalsIgnoreCase("decrease font size")) {
            System.out.println("Font size: " + --fontSize);
            return;
        }

        if (string.equalsIgnoreCase("erase formatting")) {
            System.out.println("Formatting erased for text.");
            return;
        }

        if (string.equalsIgnoreCase("bold")) {
            System.out.println("Boldened text.");
            return;
        }

        if (string.equalsIgnoreCase("italic")) {
            System.out.println("Italicized text.");
            return;
        }

        if (string.equalsIgnoreCase("underline")) {
            System.out.println("Underlined text.");
            return;
        }

        if (string.equalsIgnoreCase("strikethrough")) {
            System.out.println("Strikethroughed text.");
            return;
        }

        // HOME/PARAGRAPH
        if (string.equalsIgnoreCase("align left")) {
            System.out.println("Text aligned left.");
            return;
        }

        if (string.equalsIgnoreCase("align right")) {
            System.out.println("Text aligned right.");
            return;
        }

        if (string.equalsIgnoreCase("justify")) {
            System.out.println("Text justified.");
            return;
        }

        if (string.equalsIgnoreCase("indent")) {
            System.out.println("Text indented.");
            return;
        }

        // HOME/EDITING
        if (string.equalsIgnoreCase("find")) {
            String key = getInput("Search term");
            System.out.println("Found: " + currentString.contains(key));
            return;
        }

        if (string.equalsIgnoreCase("replace")) {
            System.out.println("Disabled by administrator.");
            return;
        }

        if (string.equalsIgnoreCase("select")) {
            System.out.println("Text selected.");
            return;
        }

        /*
            TAB INSERT
         */

        if (string.equalsIgnoreCase("table")) {
            String rows = getInput("Rows: ");
            String columns = getInput("Columns");
            System.out.println(rows + "x" + columns + "Table inserted");
            return;
        }

        if (string.equalsIgnoreCase("illustration")) {
            System.out.println("Illustration from clipboard inserted.");
            return;
        }

        if (string.equalsIgnoreCase("text")) {
            System.out.println("Text from clipboard inserted.");
            return;
        }

        /*
            TAB DESIGN
         */

        // DESIGN/THEMES
        if (string.equalsIgnoreCase("office theme") ||
                string.equalsIgnoreCase("facet") ||
                string.equalsIgnoreCase("gallery") ||
                string.equalsIgnoreCase("integral")) {
            System.out.println(string + " applied.");
            return;
        }

        // DESIGN/VARIANTS
        if (string.equalsIgnoreCase("normal") ||
                string.equalsIgnoreCase("light") ||
                string.equalsIgnoreCase("dark")) {
            System.out.println(string + " applied.");
            return;
        }

        // DESIGN/CUSTOMIZE
        if (string.equalsIgnoreCase("slide size")) {
            System.out.println("Slide size: " + ++slideSize);
            return;
        }

        if (string.equalsIgnoreCase("format background")) {
            System.out.println("Background formatting cleared.");
            return;
        }

        System.out.println("String not found.");
    }

    public static void main(String[] args) {
        // Holds the main ribbon
        MenuItem<MenuItem<MenuItem<String>>> ribbon = new MenuItem<>("Main Menu");

        /*
            Tabs residing in the ribbon have the datatype
            MenuItem<MenuItem<String>>
         */
        MenuItem<MenuItem<String>> file = new MenuItem<>("File");
        MenuItem<MenuItem<String>> home = new MenuItem<>("Home");
        MenuItem<MenuItem<String>> insert = new MenuItem<>("Insert");
        MenuItem<MenuItem<String>> design = new MenuItem<>("Design");

        // MAIN MENU RIBBON
        ribbon.insert(file);
        ribbon.insert(home);
        ribbon.insert(insert);
        ribbon.insert(design);

        /*
            Submenus in the ribbon tabs
            MenuItem<String>
         */

        // TAB FILE
        MenuItem<String> quickOperations = new MenuItem<>("Quick Operations");
        MenuItem<String> fileOperations = new MenuItem<>("File Operations");
        MenuItem<String> others = new MenuItem<>("Others");

        file.insert(quickOperations);
        file.insert(fileOperations);
        file.insert(others);

        // SUBMENU QUICK OPERATIONS
        quickOperations.insert("Autosave");
        quickOperations.insert("Save");
        quickOperations.insert("Undo");
        quickOperations.insert("Redo");
        quickOperations.insert("Present");

        // SUBMENU FILE OPERATIONS
        fileOperations.insert("New");
        fileOperations.insert("Open");
        fileOperations.insert("Info");
        fileOperations.insert("Save");
        fileOperations.insert("Save as");
        fileOperations.insert("Print");
        fileOperations.insert("Share");
        fileOperations.insert("Export");
        fileOperations.insert("Close");

        // SUBMENU OTHERS
        others.insert("Account");
        others.insert("Feedback");
        others.insert("Options");

        // TAB HOME
        MenuItem<String> clipboard = new MenuItem<>("Clipboard");
        MenuItem<String> slides = new MenuItem<>("Slides");
        MenuItem<String> font = new MenuItem<>("Font");
        MenuItem<String> paragraph = new MenuItem<>("Paragraph");
        MenuItem<String> editing = new MenuItem<>("Editing");

        home.insert(clipboard);
        home.insert(slides);
        home.insert(font);
        home.insert(paragraph);
        home.insert(editing);

        // SUBMENU CLIPBOARD
        clipboard.insert("Paste");
        clipboard.insert("Copy");
        clipboard.insert("Format Painter");

        // SUBMENU SLIDES
        slides.insert("New Slide");
        slides.insert("Layout");
        slides.insert("Reset");

        // SUBMENU FONT
        font.insert("Font Selector");
        font.insert("Increase Font Size");
        font.insert("Decrease Font Size");
        font.insert("Erase Formatting");
        font.insert("Bold");
        font.insert("Italic");
        font.insert("Underline");
        font.insert("Strikethrough");

        // SUBMENU PARAGRAPH
        paragraph.insert("Align left");
        paragraph.insert("Align center");
        paragraph.insert("Align right");
        paragraph.insert("Justify");
        paragraph.insert("Indent");

        // SUBMENU EDITING
        editing.insert("Find");
        editing.insert("Replace");
        editing.insert("Select");

        // TAB INSERT
        MenuItem<String> slidesInsert = new MenuItem<>("Slides");
        MenuItem<String> tables = new MenuItem<>("Tables");
        MenuItem<String> illustrations = new MenuItem<>("Illustrations");
        MenuItem<String> text = new MenuItem<>("Text");

        insert.insert(slidesInsert);
        insert.insert(tables);
        insert.insert(illustrations);
        insert.insert(text);

        // SUBMENU SLIDES
        slidesInsert.insert("New Slide");
        slidesInsert.insert("Options");

        // SUBMENU TABLES
        tables.insert("Table");
        tables.insert("Options");

        // SUBMENU ILLUSTRATIONS
        illustrations.insert("Shapes");
        illustrations.insert("Icons");
        illustrations.insert("3D models");
        illustrations.insert("SmartArt");
        illustrations.insert("Chart");

        // SUBMENU TEXT
        text.insert("Textbox");
        text.insert("Header & Footer");
        text.insert("WordArt");
        text.insert("Date and Time");
        text.insert("Object");

        // TAB DESIGN
        MenuItem<String> themes = new MenuItem<>("Themes");
        MenuItem<String> variants = new MenuItem<>("Variants");
        MenuItem<String> customize = new MenuItem<>("Customize");

        design.insert(themes);
        design.insert(variants);
        design.insert(customize);

        // SUBMENU THEMES
        themes.insert("Office Theme");
        themes.insert("Facet");
        themes.insert("Gallery");
        themes.insert("Integral");

        // SUBMENU VARIANTS
        variants.insert("Normal");
        variants.insert("Light");
        variants.insert("Dark");

        // SUBMENU CUSTOMIZE
        customize.insert("Slide Size");
        customize.insert("Format Background");
        System.out.println("");
        System.out.println("Simulation of: Microsoft Powerpoint\n");
        System.out.println("Written by:");
        for(String author : authors) {
            System.out.println(author);
        }

        fileName += getInput("\nFile name");

        do {
            var mainMenu = navigateRibbon(ribbon);
            var selectedTab = navigateTabs(mainMenu);
            var selectedSubMenu = navigateSubmenu(selectedTab);
            stringHandler(selectedSubMenu);
        } while (!getInput("Input X to stop operations").equalsIgnoreCase("x"));
    }
}