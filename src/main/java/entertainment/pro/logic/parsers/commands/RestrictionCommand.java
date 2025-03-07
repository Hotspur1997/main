package entertainment.pro.logic.parsers.commands;

import entertainment.pro.storage.user.ProfileCommands;
import entertainment.pro.ui.Controller;
import entertainment.pro.ui.MovieHandler;
import entertainment.pro.commons.enums.CommandKeys;
import entertainment.pro.logic.parsers.CommandStructure;
import entertainment.pro.logic.parsers.CommandSuper;

import java.io.IOException;

public class RestrictionCommand extends CommandSuper {
    public RestrictionCommand(Controller uiController) {
        super(CommandKeys.RESTRICTION, CommandStructure.cmdStructure.get(CommandKeys.RESTRICTION), uiController);
    }

    @Override
    public void executeCommands() throws IOException {
        switch (this.getSubRootCommand()) {
        case ADD:
            executeAddRestriction();
            break;
        case REMOVE:
            executeRemoveRestriction();
            break;
        case CLEAR:
            executeClearRestriction();
            break;
        default:
            break;
        }
    }

    /**
     * add to user preference.
     * root: preference
     * sub: add
     * payload: none
     * flag: -g (genre name -- not genre ID)
     */
    private void executeAddRestriction() throws IOException {
        MovieHandler movieHandler = ((MovieHandler)this.getUiController());
        ProfileCommands command = new ProfileCommands(movieHandler.getUserProfile());
        command.addRestriction(this.getFlagMap());
        movieHandler.clearSearchTextField();
        movieHandler.setLabels();
    }

    /**
     * remove from user preference.
     * root: preference
     * sub: remove
     * payload: none
     * flag: -g (genre name -- not genre ID)
     */
    private void executeRemoveRestriction() throws IOException {
        MovieHandler movieHandler = ((MovieHandler)this.getUiController());
        ProfileCommands command = new ProfileCommands(movieHandler.getUserProfile());
        command.removeRestriction(this.getFlagMap());
        movieHandler.clearSearchTextField();
        movieHandler.setLabels();
    }

    /**
     * clear all preference that was set previously.
     * root: preference
     * sub: clear
     * payload: none
     * flag: -g (genre name -- not genre ID)
     *       -a (adult -- yes to allow adult content, no to restrict, set to yes by default)
     */
    private void executeClearRestriction() throws IOException {
        MovieHandler movieHandler = ((MovieHandler)this.getUiController());
        ProfileCommands command = new ProfileCommands(movieHandler.getUserProfile());
        command.clearRestriction(this.getFlagMap());
        movieHandler.clearSearchTextField();
        movieHandler.setLabels();
    }
}