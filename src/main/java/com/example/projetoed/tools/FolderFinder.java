package com.example.projetoed.tools;

import java.io.File;

/**
 * The {@code FolderFinder} class search recursivly for the desired folder.
 */
public class FolderFinder {
    /**
     * Finds the absolute path of the desired folder by starting from the given
     * initial path and the folder name.
     * 
     * @param origin A String representing the initial path of the folder to be searched.
     * @param target A String representing the name of the searched folder.
     * @return A String containing the absolute path of the folder, or null if the
     *         folder was not found.
     */
    public static String findAbsolutePath(String origin, String target) {
        File start = new File(origin);
        return recursiveFinder(start, target).getAbsolutePath();
    }

    /**
     * Finds the absolute path of the desired folder by starting from the given
     * initial File object and the folder name.
     *
     * @param origin A File representing the initial folder to be searched.
     * @param target A String representing the name of the searched folder.
     * @return A String containing the absolute path of the folder, or null if the
     *         folder was not found.
     */
    public static String findAbsolutePath(File origin, String target) {
        return recursiveFinder(origin, target).getAbsolutePath();
    }

    /**
     * Recursively searches for the desired folder starting from the given File
     * object and folder name.
     *
     * @param origin A File representing the folder to be searched.
     * @param target A String representing the name of the folder to be searched.
     * @return A File object representing the desired folder if found, or null if
     *         the folder was not found.
     */
    private static File recursiveFinder(File origin, String target) {
        if (!origin.isDirectory())
            return null;

        if (origin.getName().equals(target))
            return origin;

        File[] subFolders = origin.listFiles();
        if (subFolders != null) {
            for (File folder : subFolders) {
                File targetFolder = recursiveFinder(folder, target);
                if (targetFolder != null) {
                    return targetFolder;
                }
            }
        }
        return null;
    }
}
