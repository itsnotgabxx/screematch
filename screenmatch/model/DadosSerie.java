package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao,
                         @JsonAlias("Genre") String genero,
                         @JsonAlias("Actors") String atores,
                         @JsonAlias("Poster") String poster,
                         @JsonAlias("Plot") String sinopse) {
}
//
//"Title": "Euphoria",
//        "Year": "2019–",
//        "Rated": "TV-MA",
//        "Released": "16 Jun 2019",
//        "Runtime": "55 min",
//        "Genre": "Drama",
//        "Director": "N/A",
//        "Writer": "Sam Levinson",
//        "Actors": "Zendaya, Hunter Schafer, Jacob Elordi",
//        "Plot": "A look at life for a group of high school students as they grapple with issues of drugs, sex, and violence.",
//        "Language": "English",
//        "Country": "United States",
//        "Awards": "Won 9 Primetime Emmys. 43 wins & 111 nominations total",
//        "Poster":