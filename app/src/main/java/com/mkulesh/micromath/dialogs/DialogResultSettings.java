/*******************************************************************************
 * microMathematics Plus - Extended visual calculator
 * *****************************************************************************
 * Copyright (C) 2014-2017 Mikhail Kulesh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.mkulesh.micromath.dialogs;

import android.app.Activity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.CheckBox;

import com.mkulesh.micromath.plus.R;
import com.mkulesh.micromath.properties.ResultProperties;
import com.mkulesh.micromath.properties.ResultPropertiesChangeIf;
import com.mkulesh.micromath.widgets.HorizontalNumberPicker;

public class DialogResultSettings extends DialogBase
{
    private final ResultPropertiesChangeIf changeIf;
    private final ResultProperties properties;
    private final AppCompatEditText units;
    private final CheckBox disableCalculation, hideResultField;
    private final HorizontalNumberPicker arrayLengthPicker;

    public DialogResultSettings(Activity context, ResultPropertiesChangeIf changeIf, ResultProperties properties)
    {
        super(context, R.layout.dialog_result_settings, R.string.dialog_result_title);

        this.changeIf = changeIf;
        this.properties = properties;

        units = findViewById(R.id.dialog_result_units_field);
        units.setText(properties.units);

        disableCalculation = findViewById(R.id.dialog_result_disable_calculation);
        disableCalculation.setChecked(properties.disableCalculation);

        hideResultField = findViewById(R.id.dialog_result_hide_result_field);
        hideResultField.setChecked(properties.hideResultField);

        arrayLengthPicker = findViewById(R.id.dialog_result_array_length_picker);
        arrayLengthPicker.setVisibility(properties.showArrayLength ? View.VISIBLE : View.GONE);
        arrayLengthPicker.setValue(properties.arrayLength);
        arrayLengthPicker.minValue = 2;
    }

    @Override
    public void onClick(View v)
    {
        boolean isChanged = false;
        if (v.getId() == R.id.dialog_button_ok)
        {
            if (changeIf != null && properties != null)
            {
                if (!properties.units.equals(units.getText().toString()))
                {
                    properties.units = units.getText().toString();
                    isChanged = true;
                }
                if (properties.disableCalculation != disableCalculation.isChecked())
                {
                    properties.disableCalculation = disableCalculation.isChecked();
                    isChanged = true;
                }
                if (properties.hideResultField != hideResultField.isChecked())
                {
                    properties.hideResultField = hideResultField.isChecked();
                    isChanged = true;
                }
                if (properties.arrayLength != arrayLengthPicker.getValue())
                {
                    properties.arrayLength = arrayLengthPicker.getValue();
                    isChanged = true;
                }
            }
        }
        changeIf.onResultPropertiesChange(isChanged);
        closeDialog();
    }

}
