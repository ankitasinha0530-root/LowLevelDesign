package com.lowlevel.design.splitwise.Expense;


import com.lowlevel.design.splitwise.Expense.Split.ExpenseSplit;
import com.lowlevel.design.splitwise.Expense.Split.Split;

import java.util.List;

public class ExpenseController {

    LowLevelDesign.DesignSplitwise.BalanceSheetController balanceSheetController;
    public ExpenseController(){
        balanceSheetController = new LowLevelDesign.DesignSplitwise.BalanceSheetController();
    }

    public Expense createExpense(String expenseId, String description, double expenseAmount,
                                 List<Split> splitDetails, ExpenseSplitType splitType, User paidByUser){

        ExpenseSplit expenseSplit = SplitFactory.getSplitObject(splitType);
        assert expenseSplit != null;
        expenseSplit.validateSplitRequest(splitDetails, expenseAmount);

        Expense expense = new Expense(expenseId, expenseAmount, description, paidByUser, splitType, splitDetails);

        balanceSheetController.updateUserExpenseBalanceSheet(paidByUser, splitDetails, expenseAmount);

        return expense;
    }
}
