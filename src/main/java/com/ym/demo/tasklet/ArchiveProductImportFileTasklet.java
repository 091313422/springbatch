package com.ym.demo.tasklet;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.ym.demo.dao.ProductTestMapper;
import com.ym.demo.model.Product;

import java.io.File;
import java.util.List;

/**
 * A tasklet that logs product information
 */
public class ArchiveProductImportFileTasklet implements Tasklet
{
    private String inputFile;
    @Autowired
    private ProductTestMapper productTestMapper;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception
    {
        // Make our destination directory and copy our input file to it
        File archiveDir = new File( "archive" );
        FileUtils.forceMkdir( archiveDir );
        FileUtils.copyFileToDirectory( new File( inputFile ), archiveDir );
        List<Product> list = productTestMapper.queryProduct(null);
        for(Product p: list) {
        	System.out.println(p.getDescription());
        }
        // We're done...
        return RepeatStatus.FINISHED;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
}
