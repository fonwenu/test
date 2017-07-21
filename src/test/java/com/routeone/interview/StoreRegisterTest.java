package com.routeone.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StoreRegisterTest {

	@InjectMocks
	StoreRegister storeRegister;

	@Mock
	ReceiptImpl mockReceipt;

	@Before
	public void setup() {
		File inventoryFile = new File(getClass().getClassLoader()
				.getResource("sample-inventory.csv").getFile());
		storeRegister.loadInventory(inventoryFile);
	}
	
	@Test
	public void testLoadsInventoryFileCorrectly() throws Exception {
		assertNotNull(storeRegister.nameToEntryMap);
		assertTrue(storeRegister.nameToEntryMap.size() > 0);
		
	}

	@Test(expected = RuntimeException.class)
	public void testInvalidInput() {
		List<String> emptyList = new ArrayList<>();
		storeRegister.checkoutOrder(emptyList);
	}

	@Test
	public void testSingleRow() throws Exception {
		List<String> singleList = Arrays.asList("PC1033");
		Receipt expected = storeRegister.checkoutOrder(singleList);
		assertEquals("$20.00", expected.getFormattedTotal());
		assertEquals(1, expected.getOrderedItems().size());
	}

	@Test
	public void testDoubleRow() throws Exception {
		List<String> doubleList = Arrays.asList("PC1033", "PC800");
		Receipt expected = storeRegister.checkoutOrder(doubleList);
		assertEquals("$29.99", expected.getFormattedTotal());
		assertEquals(2, expected.getOrderedItems().size());
	}

	@Test
	public void testMultipleRandomRows() throws Exception {
		List<String> randomList = Arrays.asList("PC1033", "PC800",
				"GenericProcessor", "GenericMotherboard",
				"GenericMotherboardV2", "LCD", "Keyboard");
		List<String> expectedItemList = Arrays.asList("GenericProcessor","GenericMotherboard",
				"GenericMotherboardV2", "LCD", "PC1033",
				"Keyboard", "PC800");
		Receipt expected = storeRegister.checkoutOrder(randomList);
		assertEquals("$434.99", expected.getFormattedTotal());
		assertEquals(7, expected.getOrderedItems().size());
		assertEquals(expectedItemList, expected.getOrderedItems());
	}


}
