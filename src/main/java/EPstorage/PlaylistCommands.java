package EPstorage;

import object.MovieInfoObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * class that contains all methods that deal with individual Playlist object and the list of Playlist objects
 */
public class PlaylistCommands {
    private ArrayList<Playlist> playlists;
    private EditPlaylistJson editPlaylistJson;

    public PlaylistCommands(ArrayList<Playlist> playlists) throws IOException {
        editPlaylistJson = new EditPlaylistJson();
        this.playlists = playlists;
    }

    /**
     * add new Playlist object into the list
     */
    public void newPlaylist(String listName) throws IOException {
        Playlist playlist = new Playlist();
        playlist.setListName(listName);
        playlist.setDescription("");
        playlist.setMoveId(new ArrayList<>(10));
        editPlaylistJson.addPlaylist(playlist);
    }

    /**
     * delete particular Playlist object from list
     */
    public void deletePlaylist(String listName) throws IOException {
        editPlaylistJson.removePlaylist(listName);
    }

    /**
     * set name/description to particular Playlist object
     */
    public void setToPlaylist(String name, TreeMap<String, ArrayList<String>> flagMap) throws IOException {
        if (flagMap.containsKey("-n") && !flagMap.containsKey("-d")) {
            setPlaylistName(name, flagMap.get("-n").get(0));
        }
        if (flagMap.containsKey("-d") && !flagMap.containsKey("-n")) {
            setPlaylistDescription(name, flagMap.get("-d").get(0));
        }
        if (flagMap.containsKey("-d") && flagMap.containsKey("-n")) {
            setBoth(name, flagMap.get("-n").get(0), flagMap.get("-d").get(0));
        }
    }

    /**
     * change name of particular Playlist object
     */
    public void setPlaylistName(String name, String newName) throws IOException {
        for (Playlist log : playlists){
            if (log.getListName().equals(name)) {
                playlists.remove(log);
                log.setListName(newName);
                playlists.add(log);
                editPlaylistJson.editPlaylist(playlists);
                break;
            }
        }
    }

    /**
     * change description of particular Playlist object
     */
    public void setPlaylistDescription(String name, String description) throws IOException {
        for (Playlist log : playlists){
            if (log.getListName().equals(name)) {
                playlists.remove(log);
                log.setDescription(description);
                playlists.add(log);
                editPlaylistJson.editPlaylist(playlists);
                break;
            }
        }
    }

    /**
     * to allow setting of both name and description at the same time
     */
    public void setBoth(String name, String newName, String description) throws IOException {
        for (Playlist log : playlists) {
            if (log.getListName().equals(name)) {
                playlists.remove(log);
                log.setDescription(description);
                log.setListName(newName);
                playlists.add(log);
                editPlaylistJson.editPlaylist(playlists);
                break;
            }
        }
    }

    /**
     * add movies to particular Playlist object
     */
    public void addToPlaylist(String name, TreeMap<String, ArrayList<String>> flagMap, ArrayList<MovieInfoObject> mMovies) throws IOException {
        ArrayList<Long> userMovies = new ArrayList<>(20);
        for (String log : flagMap.get("-m")){
            int index = Integer.parseInt(log);
            userMovies.add((mMovies.get(--index)).getID());
        }
        for (Playlist log : playlists) {
            if (log.getListName().equals(name)) {
                playlists.remove(log);
                log.addMovieId(userMovies);
                playlists.add(log);
                editPlaylistJson.editPlaylist(playlists);
                break;
            }
        }
    }

    /**
     * remove movies from particular Playlist object
     */
    public void removeFromPlaylist(String name, TreeMap<String, ArrayList<String>> flagMap, ArrayList<MovieInfoObject> mMovies) throws IOException {
        ArrayList<Long> userMovies = new ArrayList<>(20);
        for (String log : flagMap.get("-m")){
            int index = Integer.parseInt(log);
            userMovies.add((mMovies.get(--index)).getID());
        }
        for (Playlist log : playlists) {
            if (log.getListName().equals(name)) {
                playlists.remove(log);
                log.removeMovieId(userMovies);
                playlists.add(log);
                editPlaylistJson.editPlaylist(playlists);
                break;
            }
        }
    }
}
