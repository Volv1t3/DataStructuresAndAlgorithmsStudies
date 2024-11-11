module DataStructuresAndAlgorithm {
    requires java.naming;
    requires java.prefs;
    requires java.rmi;
    requires java.xml.crypto;
    requires org.jetbrains.annotations;
    requires junit;
    requires org.junit.jupiter.api;

    opens Trees.AVLTreeTesting to org.junit.jupiter;
}