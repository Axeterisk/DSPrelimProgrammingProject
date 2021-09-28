public class Menu {
    public static void main(String[] args) {
        MenuItem<MenuItem<MenuItem<String>>> mainMenu = new MenuItem<>("Google Slides");
        MenuItem<MenuItem<MenuItem<MenuItem<String>>>> ribbonFour = new MenuItem<>("4th Ribbon");
        mainMenu.insert(ribbonFour);
        
        MenuItem<MenuItem<MenuItem<String>>> themeRibbon = new MenuItem<>("Theme Bar Operations");
        mainMenu.insert(themeRibbon);

        MenuItem<MenuItem<String>> edit = new MenuItem<>("Edit Theme");
        themeRibbon.insert(edit);
        MenuItem<String> simpleL = new MenuItem<>("Simple Light");
        edit.insert(simpleL);
        MenuItem<String> simpleD = new MenuItem<>("Simple Dark");
        edit.insert(simpleD);
        MenuItem<String> streamL = new MenuItem<>("Streamline");
        edit.insert(streamL);
        MenuItem<String> focus = new MenuItem<>("Focus");
        edit.insert(focus);
        MenuItem<String> shift = new MenuItem<>("Shift");
        edit.insert(shift);
        MenuItem<String> momentum = new MenuItem<>("Momentum");
        edit.insert(momentum);
        MenuItem<String> paradigm = new MenuItem<>("Paradigm");
        edit.insert(paradigm);
        MenuItem<String> material = new MenuItem<>("Material");
        edit.insert(material);
        MenuItem<String> swiss = new MenuItem<>("Swiss");
        edit.insert(swiss);
        MenuItem<String> beachD = new MenuItem<>("Beach Day");
        edit.insert(beachD);
        MenuItem<String> slate = new MenuItem<>("Slate");
        edit.insert(slate);
        MenuItem<String> coral = new MenuItem<>("Coral");
        edit.insert(coral);
        MenuItem<String> spearM = new MenuItem<>("Spearmint");
        edit.insert(spearM);
        MenuItem<String> plum = new MenuItem<>("Plum");
        edit.insert(plum);
        MenuItem<String> paperB = new MenuItem<>("Paperback");
        edit.insert(paperB);
        MenuItem<String> modernW = new MenuItem<>("Modern Writer");
        edit.insert(modernW);
        MenuItem<String> geometric = new MenuItem<>("Geomtric");
        edit.insert(geometric);
        MenuItem<String> pop = new MenuItem<>("Pop");
        edit.insert(pop);
        MenuItem<String> luxe = new MenuItem<>("Luxe");
        edit.insert(luxe);
        MenuItem<String> blueGold = new MenuItem<>("Blue & Gold");
        edit.insert(blueGold);
        MenuItem<String> tropic = new MenuItem<>("Tropic");
        edit.insert(tropic);
        MenuItem<String> marina = new MenuItem<>("Marina");
        edit.insert(marina);
        MenuItem<String> gameD = new MenuItem<>("Game Day");
        edit.insert(gameD);

        MenuItem<MenuItem<String>> change = new MenuItem<>("Edit Theme");
        themeRibbon.insert(change);

        MenuItem<MenuItem<String>> imp = new MenuItem<>("Import Theme");
        themeRibbon.insert(imp);

        

        /*  TO TEST PROGRAM:
            mainMenu.insert() to add every ribbon to the main menu
            mainMenu.navigate()
         */
    }

}
