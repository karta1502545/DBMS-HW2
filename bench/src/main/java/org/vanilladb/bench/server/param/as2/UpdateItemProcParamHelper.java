/*******************************************************************************
 * Copyright 2016, 2018 vanilladb.org contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.vanilladb.bench.server.param.as2;

import org.vanilladb.core.sql.DoubleConstant;
import org.vanilladb.core.sql.IntegerConstant;
import org.vanilladb.core.sql.Schema;
import org.vanilladb.core.sql.Type;
import org.vanilladb.core.sql.VarcharConstant;
import org.vanilladb.core.sql.storedprocedure.SpResultRecord;
import org.vanilladb.core.sql.storedprocedure.StoredProcedureParamHelper;

public class UpdateItemProcParamHelper extends StoredProcedureParamHelper {

	private int updateCount;
	private int[] updateItemId;
	private double[] raises;

	public class payload {
		int updateNum;
		int id;
		double price_raise;

		payload(int updateNum, int id, double price_raise) {
			this.updateNum = updateNum;
			this.id = id;
			this.price_raise = price_raise;
		}
	};

	public int getUpdateCount() {
		return updateCount;
	}

	public int getUpdateItemId(int index) {
		return updateItemId[index];
	}

	public double getRaise(int index) {
		return raises[index];
	}

	@Override
	public void prepareParameters(Object... pars) {

		// Show the contents of paramters
	   //System.out.println("Params: " + Arrays.toString(pars));

		updateCount = ((payload) pars[0]).updateNum;
		updateItemId = new int[updateCount];
		raises = new double[updateCount];

		for (int i = 1; i <= updateCount; i++) {
			updateItemId[i] = ((payload) pars[0]).id;
			raises[i] = ((payload) pars[0]).price_raise;
		}
	}

	@Override
	public Schema getResultSetSchema() {
		return new Schema();
	}

	@Override
	public SpResultRecord newResultSetRecord() {
		return new SpResultRecord();
	}

}
