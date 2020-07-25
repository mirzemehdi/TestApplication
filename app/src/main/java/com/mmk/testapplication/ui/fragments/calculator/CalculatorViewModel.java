package com.mmk.testapplication.ui.fragments.calculator;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.mmk.testapplication.manager.PreferencesManager;
import com.mmk.testapplication.repository.CalculatorRepository;
import com.mmk.testapplication.utils.Constants;

import static com.mmk.testapplication.utils.Constants.KEY_EXPRESSION;
import static com.mmk.testapplication.utils.Constants.KEY_RESULT;

public class CalculatorViewModel extends AndroidViewModel {
    private final CalculatorRepository calculatorRepository;
    private final PreferencesManager preferencesManager;
    private final MutableLiveData<String> expression = new MutableLiveData<>();
    private final MutableLiveData<String> resultLiveData = new MutableLiveData<>();
    private final MutableLiveData<Void> equalButton = new MutableLiveData<>();


    @ViewModelInject
    public CalculatorViewModel(@NonNull Application application,
                               CalculatorRepository calculatorRepository,
                               PreferencesManager preferencesManager) {
        super(application);
        this.calculatorRepository = calculatorRepository;
        this.preferencesManager=preferencesManager;
        expression.setValue(preferencesManager.getSavedString(KEY_EXPRESSION));
        resultLiveData.setValue(preferencesManager.getSavedString(KEY_RESULT));
    }

    public LiveData<Void> getEqualButton() {
        return equalButton;
    }

    public MutableLiveData<String> getExpression() {

        return expression;

    }

    public LiveData<String> getResult() {
        return Transformations.map(expression, input -> {
            String expressionValue = getExpressionLiveDataValue();
            try {
                String result =calculatorRepository.calculateExpression(expressionValue);
                resultLiveData.setValue(result);
                preferencesManager.saveString(KEY_EXPRESSION,getExpressionLiveDataValue());
                preferencesManager.saveString(KEY_RESULT,getResultLiveDataValue());
                return result;
            } catch (NumberFormatException e) {
                return resultLiveData.getValue();
            }

        });
    }

    public void onButtonClicked(String value) {
        String currentExpr = getExpressionLiveDataValue();
        String finalExpr = calculatorRepository.getExpression(currentExpr, value);
        if (value.equals("=")) {
            equalButton.setValue(null);
            expression.setValue(getResultLiveDataValue());

        } else
            expression.setValue(finalExpr);
    }

    private String getExpressionLiveDataValue() {
        if (expression.getValue() == null) return "";
        else return expression.getValue();
    }

    private String getResultLiveDataValue() {
        if (resultLiveData.getValue() == null) return "";
        else return resultLiveData.getValue();
    }


}
