package com.github.lvoltolini.FinanceCalculator.Generic.GenericPipeline;


import com.github.lvoltolini.FinanceCalculator.Generic.GenericData.DataMatrix;

public interface PipelineStep
{
    DataMatrix process(DataMatrix input);
}
