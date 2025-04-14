#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Administrateur
#------------------------------------------------------------

CREATE TABLE Administrateur(
        ID_admin   Int  Auto_increment  NOT NULL ,
        mail_admin Varchar (50) NOT NULL ,
        mdp_admin  Varchar (50) NOT NULL
	,CONSTRAINT Administrateur_PK PRIMARY KEY (ID_admin)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Attraction
#------------------------------------------------------------

CREATE TABLE Attraction(
        ID_attraction          Int  Auto_increment  NOT NULL ,
        nom_attraction         Varchar (50) NOT NULL ,
        description_attraction Varchar (50) NOT NULL ,
        prix_attraction        Float NOT NULL
	,CONSTRAINT Attraction_PK PRIMARY KEY (ID_attraction)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
        ID_client     Int  Auto_increment  NOT NULL ,
        mail_client   Varchar (50) NOT NULL ,
        mdp_client    Varchar (50) NOT NULL ,
        nom_client    Varchar (50) NOT NULL ,
        prenom_client Varchar (50) NOT NULL
	,CONSTRAINT Client_PK PRIMARY KEY (ID_client)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Reduction
#------------------------------------------------------------

CREATE TABLE Reduction(
        ID_reduction          Int  Auto_increment  NOT NULL ,
        nom_reduction         Varchar (50) NOT NULL ,
        pourcentage_reduction Varchar (50) NOT NULL ,
        type_reduction        Int NOT NULL COMMENT "type_reduction : 1 - client fréquent 2 - client enfant 3 - client sénior" 
	,CONSTRAINT Reduction_PK PRIMARY KEY (ID_reduction)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Billet
#------------------------------------------------------------

CREATE TABLE Billet(
        nom_billet            Varchar (50) NOT NULL ,
        prenom_billet         Varchar (50) NOT NULL ,
        date_billet           Int NOT NULL ,
        date_naissance_billet Date NOT NULL
	,CONSTRAINT Billet_PK PRIMARY KEY (nom_billet,prenom_billet)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Reservation
#------------------------------------------------------------

CREATE TABLE Reservation(
        ID_reservation    Int  Auto_increment  NOT NULL ,
        date_reservation  Date NOT NULL ,
        ID_client         Int NOT NULL ,
        ID_attraction     Int NOT NULL ,
        ID_client_Reserve Int NOT NULL ,
        nom_billet        Varchar (50) NOT NULL ,
        prenom_billet     Varchar (50) NOT NULL
	,CONSTRAINT Reservation_AK UNIQUE (ID_client,ID_attraction)
	,CONSTRAINT Reservation_PK PRIMARY KEY (ID_reservation)

	,CONSTRAINT Reservation_Client_FK FOREIGN KEY (ID_client_Reserve) REFERENCES Client(ID_client)
	,CONSTRAINT Reservation_Billet0_FK FOREIGN KEY (nom_billet,prenom_billet) REFERENCES Billet(nom_billet,prenom_billet)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Ajouter_Supprimer
#------------------------------------------------------------

CREATE TABLE Ajouter_Supprimer(
        ID_admin      Int NOT NULL ,
        ID_attraction Int NOT NULL ,
        ID_reduction  Int NOT NULL
	,CONSTRAINT Ajouter_Supprimer_PK PRIMARY KEY (ID_admin,ID_attraction,ID_reduction)

	,CONSTRAINT Ajouter_Supprimer_Administrateur_FK FOREIGN KEY (ID_admin) REFERENCES Administrateur(ID_admin)
	,CONSTRAINT Ajouter_Supprimer_Attraction0_FK FOREIGN KEY (ID_attraction) REFERENCES Attraction(ID_attraction)
	,CONSTRAINT Ajouter_Supprimer_Reduction1_FK FOREIGN KEY (ID_reduction) REFERENCES Reduction(ID_reduction)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Propose
#------------------------------------------------------------

CREATE TABLE Propose(
        ID_attraction  Int NOT NULL ,
        ID_reservation Int NOT NULL
	,CONSTRAINT Propose_PK PRIMARY KEY (ID_attraction,ID_reservation)

	,CONSTRAINT Propose_Attraction_FK FOREIGN KEY (ID_attraction) REFERENCES Attraction(ID_attraction)
	,CONSTRAINT Propose_Reservation0_FK FOREIGN KEY (ID_reservation) REFERENCES Reservation(ID_reservation)
)ENGINE=InnoDB;

