package org.knowrob.interfaces.mongo.types;

import java.util.Date;

import javax.vecmath.Matrix4d;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;
import tfjava.Stamped;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


public class PoseStampedFactory {
	
	/**
	 * Read from a MongoDB result object
	 * 
	 * @param row BasicDBObject, e.g. result of a MongoDB query
	 * @return Instance of PoseStamped with values from row
	 */
	public static Stamped<Matrix4d> readFromDBObject(DBObject row) {

		Stamped<Matrix4d> p = new Stamped<Matrix4d>();
		
		BasicDBObject header = (BasicDBObject) row.get("header");
		BasicDBObject pose   = (BasicDBObject) row.get("pose");

		// return null for empty poses
		if(header==null || pose == null)
			return null;
		
		p.frameID = header.getString("frame_id");
		
		Object stamp = header.get("stamp");
		if(stamp instanceof Date) {
			p.timeStamp = new ISODate((Date)stamp).toROSTime();
		}
		else if(stamp instanceof Double) {
			Double miliSeconds = ((Double)stamp)*1000.0;
			p.timeStamp = new ISODate(miliSeconds.longValue()).toROSTime();
			
		}
		else if(stamp instanceof Long) {
			p.timeStamp = new ISODate(((Long)stamp).longValue()).toROSTime();
		}
		else {
			System.err.println("Unknown date type: " + stamp.getClass().getName());
			p.timeStamp = new ISODate(0l).toROSTime();
		}
		

		Vector3d v = new Vector3d();
		v.x = ((BasicDBObject) pose.get("position")).getDouble("x");
		v.y = ((BasicDBObject) pose.get("position")).getDouble("y");
		v.z = ((BasicDBObject) pose.get("position")).getDouble("z");
		
		Quat4d q = new Quat4d();
		q.x = ((BasicDBObject) pose.get("orientation")).getDouble("x");
		q.y = ((BasicDBObject) pose.get("orientation")).getDouble("y");
		q.z = ((BasicDBObject) pose.get("orientation")).getDouble("z");
		q.w = ((BasicDBObject) pose.get("orientation")).getDouble("w");
		
		Matrix4d mat = new Matrix4d(q, v, 1.0);
		p.setData(mat);
		
		return p;
	}
	
}
