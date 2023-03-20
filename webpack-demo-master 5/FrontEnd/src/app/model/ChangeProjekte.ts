export interface ChangeProjekte {

  projekt_id: number;
  titel: string;
  inhalt: string;
  ziel: string;
  status: number;
  budget: number;
  start_datum: string;
  end_datum: string;
  kategorie_id: {
    kategorien_id: any;
  }

}
