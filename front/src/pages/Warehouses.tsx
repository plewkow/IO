import WarehouseTable from '../components/layouts/resources/WarehousesTable.tsx';
import { Warehouse } from '../types/index';
import { useState, useEffect } from 'react';
import api from "../api/Axios.tsx"

export const Warehouses = () => {
    const [warehouses, setWarehouses] = useState<Warehouse[]>([]);

    useEffect(() => {
        const fetchWarehouses = async () => {
            try {
                const response = await api.get("/warehouses");
                setWarehouses(response.data);
            } catch (error) {
                console.error("Failed to fetch warehouses:", error);
            }
        };

        fetchWarehouses();
    }, []);

    return (
        <div className="min-h-screen">
            <h1 className="text-bold text-5xl text-center my-8">List of Warehouses</h1>
            <WarehouseTable warehouses={warehouses}/>
        </div>
    );
};
