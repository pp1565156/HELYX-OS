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
package eu.engys.core.project.zero.fields;

import eu.engys.util.bean.AbstractBean;

public class FixedVectorInitialisation extends AbstractBean implements Initialisation {

    public static final String VALUE_KEY = "value";
    public static final double[] DEFAULT_VALUE = {0,0,0};
    private double[] value = DEFAULT_VALUE;

    public FixedVectorInitialisation() {}
    
    public FixedVectorInitialisation(double v1, double v2, double v3) {
        this.value = new double[]{v1, v2, v3};
    }
    
    public double[] getValue() {
        return value;
    }
    public void setValue(double[] value) {
        firePropertyChange(VALUE_KEY, this.value, this.value = value);
    }

    public String toString() {
        return "Fixed Value";
    }
}