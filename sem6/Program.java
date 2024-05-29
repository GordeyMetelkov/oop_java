import java.util.ArrayList;

public class Program {
  public static void main(String[] args) {
    FilmFinder finder = new FilmFinder();
  }
}

class FilmFinder {
  public FilmFinder() {
    init();
  }

  DataBase db;

  public DataBase getDb() {
    return db;
  }

  public String getAllInfoBy(int id) {
    Cinema c = db.films.get(id - 1);

    return String.format("%d %s %s %s",
        c.id,
        c.name,
        db.genres.get(c.genreId - 1).name,
        db.studios.get(c.creatorId - 1).titleName);
  }

  DataBase init() {
    db = new DataBase();
    Cinema c1 = new Cinema(1, "Wolves", 1, 1);
    Cinema c2 = new Cinema(2, "Spurs", 1, 2);
    Cinema c3 = new Cinema(3, "Man City", 3, 4);
    Cinema c4 = new Cinema(4, "Gunners", 3, 3);

    db.films.add(c1);
    db.films.add(c2);
    db.films.add(c3);
    db.films.add(c4);

    db.genres.add(new Genre(1, "Action"));
    db.genres.add(new Genre(2, "Comedy"));
    db.genres.add(new Genre(3, "Drama"));
    FilmCreatorFactory pf = new FilmCreatorFactory();
    db.addStudios(pf.getFilmProducer("21Fox"));
    db.addStudios(pf.getFilmProducer("Worner"));
    db.addStudios(pf.getFilmProducer("Marvel"));
    db.addStudios(pf.getFilmProducer("DC"));

    return db;
  }
}

class DataBase {
  ArrayList<Cinema> films = new ArrayList<>();
  ArrayList<Studio> studios = new ArrayList<>();
  ArrayList<Genre> genres = new ArrayList<>();

  public void addStudios(Studio producer) {
    studios.add(producer);
  }
}

class Cinema {
  int id;
  int creatorId;
  String name;
  int genreId;

  public Cinema(int cinemaId, String name, int genreId, int creatorId) {
    this.id = cinemaId;
    this.creatorId = creatorId;
    this.name = name;
    this.genreId = genreId;
  }
}

class Studio {
  int id;
  String titleName;
}

class Genre {
  int id;
  String name;

  public Genre(int id, String name) {
    this.id = id;
    this.name = name;
  }
}

class FilmCreatorFactory {
  int count = 1;

  public Studio getFilmProducer(String name) {
    Studio fp = new Studio();
    fp.id = count++;
    fp.titleName = name;
    return fp;
  }
}
