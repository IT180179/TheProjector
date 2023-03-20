export interface ChangeMeilenstein{

  meilensteine_id: number;
  titel: string;
  beschreibung: string;
  status: number;
  end_datum: string;
  projekt_id: {
    projekt_id: any
  }
}
