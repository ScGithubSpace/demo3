/*
SPDX-License-Identifier: Apache-2.0
*/

package org.example.fabric;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.hyperledger.fabric.sdk.Peer;

public class ClientApp {

	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}

	public static void main(String[] args) throws Exception {
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallets.newFileSystemWallet(walletPath);
		// load a CCP
		Path networkConfigPath = Paths.get("src/main/resources/connection-org1.yaml");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {

			// get the network and contract
			Network network = gateway.getNetwork("mychannel");

			Contract contract = network.getContract("commit");
			byte[] result;

			result = contract.evaluateTransaction("QueryStudentInfo","43");
			System.out.println(new String(result));

			//contract.submitTransaction("CreateStudentInfo", "0043", "0089", "0023", "Grey", "Mary", "commit05");

//
//			contract.submitTransaction("changeCarOwner", "CAR10", "Archie");
//
//			result = contract.evaluateTransaction("queryCar", "CAR10");
//			System.out.println(new String(result));
		}
	}

}
