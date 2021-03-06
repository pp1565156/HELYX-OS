/*******************************************************************************
 *  |       o                                                                   |
 *  |    o     o       | HELYX-OS: The Open Source GUI for OpenFOAM             |
 *  |   o   O   o      | Copyright (C) 2012-2016 ENGYS                          |
 *  |    o     o       | http://www.engys.com                                   |
 *  |       o          |                                                        |
 *  |---------------------------------------------------------------------------|
 *  |   License                                                                 |
 *  |   This file is part of HELYX-OS.                                          |
 *  |                                                                           |
 *  |   HELYX-OS is free software; you can redistribute it and/or modify it     |
 *  |   under the terms of the GNU General Public License as published by the   |
 *  |   Free Software Foundation; either version 2 of the License, or (at your  |
 *  |   option) any later version.                                              |
 *  |                                                                           |
 *  |   HELYX-OS is distributed in the hope that it will be useful, but WITHOUT |
 *  |   ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or   |
 *  |   FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License   |
 *  |   for more details.                                                       |
 *  |                                                                           |
 *  |   You should have received a copy of the GNU General Public License       |
 *  |   along with HELYX-OS; if not, write to the Free Software Foundation,     |
 *  |   Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA            |
 *******************************************************************************/
package eu.engys.core.controller.actions;

import static eu.engys.core.project.openFOAMProject.LOG;

import java.io.File;
import java.nio.file.Paths;

import eu.engys.core.controller.Controller;
import eu.engys.core.controller.ScriptFactory;
import eu.engys.core.project.Model;
import eu.engys.core.project.openFOAMProject;
import eu.engys.util.IOUtils;

public class RunMesh extends AbstractRunCommand {

    public static final String ACTION_NAME = "Create Mesh";
    public static final String LOG_NAME = "snappyHexMesh.log";

    protected final ScriptFactory scriptFactory;
    protected File logFile;

    public RunMesh(Model model, Controller controller, ScriptFactory scriptFactory) {
        super(model, controller);
        this.scriptFactory = scriptFactory;
    }

    @Override
    public void beforeExecute() {
        this.logFile = Paths.get(model.getProject().getBaseDir().getAbsolutePath(), LOG, LOG_NAME).toFile();
        IOUtils.clearFile(logFile);
        clearPolyMesh();
        setupLogFolder();
        fixDecomposeParDict(model);
    }

    private void fixDecomposeParDict(Model model) {
        model.getProject().getSystemFolder().getDecomposeParDict().toHierarchical(model);
    }

    private void clearPolyMesh() {
        model.getProject().getZeroFolder().deleteMesh();
    }

    private void setupLogFolder() {
        File log = new File(model.getProject().getBaseDir(), openFOAMProject.LOG);
        if (!log.exists()) {
            log.mkdir();
        }
    }

}
