/*
 * Copyright (c) 2014 Red Hat, Inc. and/or its affiliates.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Cheng Fang - Initial API and implementation
 */

package org.jberet.support._private;

import java.io.File;
import javax.batch.operations.BatchRuntimeException;

import org.jboss.logging.BasicLogger;
import org.jboss.logging.Logger;
import org.jboss.logging.annotations.Cause;
import org.jboss.logging.annotations.LogMessage;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageLogger;
import org.jboss.logging.annotations.ValidIdRange;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.util.CsvContext;

@MessageLogger(projectCode = "JBERET")
@ValidIdRange(min = 60000, max = 60999)
public interface SupportLogger extends BasicLogger {
    SupportLogger LOGGER = Logger.getMessageLogger(SupportLogger.class, "org.jberet.support");

    @Message(id = 60000, value = "Invalid CSV preference value %s for key %s")
    BatchRuntimeException invalidCsvPreference(String csvPref, String key);

    @Message(id = 60001, value = "Failed to read header from CSV resource %s")
    BatchRuntimeException failToReadCsvHeader(@Cause Throwable th, String csvResource);

    @Message(id = 60002, value = "Failed to load or instantiate custom type based on property value %s")
    BatchRuntimeException failToLoadOrCreateCustomType(@Cause Throwable th, String contextStringVal);

    @Message(id = 60003, value = "Invalid property value format (missing quote): %s")
    BatchRuntimeException missingQuote(String propertyVal);

    @Message(id = 60004, value = "Failed to open stream from resource: %s")
    BatchRuntimeException failToOpenStream(@Cause Throwable throwable, String resource);

    @Message(id = 60005, value = "Invalid position %s to start reading, the configured range is between %s and %s")
    BatchRuntimeException invalidStartPosition(int startPosition, int start, int end);

    @Message(id = 60006, value = "The number of CellProcessor %s and the number of headers %s are different.")
    BatchRuntimeException numberOfCellProcessorsAndHeaderDiff(int cellProcessorCount, int headerCount);

    @Message(id = 60007, value = "Unexpected character %s at position %s in character array %s")
    BatchRuntimeException unexpectedChar(char ch, int position, String chars);

    @Message(id = 60008, value = "Invalid parameters for CellProcessor: %s %s")
    BatchRuntimeException invalidParamsForCellProcessor(String cellProcessorName, String[] params);

    @Message(id = 60009, value = "Unsupported CellProcessor: %s %s")
    BatchRuntimeException unsupportedCellProcessor(String cellProcessorName, String[] params);

    @Message(id = 60010, value = "The target CSV resource already exists: %s")
    BatchRuntimeException csvResourceAlreadyExists(Object targetCsvResource);

    @Message(id = 60011, value = "The target CSV resource is a directory: %s")
    BatchRuntimeException csvResourceIsDirectory(File file);

    @Message(id = 60012, value = "The existing transient user data in step is not of type String: %s")
    BatchRuntimeException cannotAppendToNonStringData(Class<?> dataType);

    @Message(id = 60013, value = "The CellProcessor value may be missing an ending single quote: %s")
    @LogMessage(level = Logger.Level.WARN)
    void maybeMissingEndQuote(String line);

    @Message(id = 60014, value = "Failed to parse string %s to enum %s in CsvContext %s for CellProcessor %s")
    BatchRuntimeException failToParseEnum(@Cause Throwable throwable, Object val, String enumType, CsvContext context, CellProcessor cellProcessor);

}