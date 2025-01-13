import React, { useEffect, useState } from 'react';
import { FinancialNeed } from './type';
import { StatusChangeButton } from './components/StatusChangeButton';

export const FinancialNeedsList: React.FC = () => {
    const [needs, setNeeds] = useState<FinancialNeed[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    const fetchNeeds = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/financial-needs');
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data: FinancialNeed[] = await response.json();
            setNeeds(data);
        } catch (error) {
            if (error instanceof Error) {
                setError(error.message);
            } 
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchNeeds();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="space-y-4">
            {needs
                .sort((a, b) => a.id - b.id)
                .map((need) => (
                    <div key={need.id} className="p-4 border rounded-lg shadow-sm">
                        <p className="mb-1">ID: {need.id}</p>
                        <p className="mb-1">Description: {need.description}</p>
                        <p className="mb-1">Goal: {need.collectionGoal}</p>
                        <p className="mb-1">Status: {need.status}</p>
                        <p className="mb-1">Priority: {need.priority}</p>
                        <StatusChangeButton
                            needId={need.id}
                            currentStatus={need.status}
                            needType="financial"
                            onStatusChange={fetchNeeds}
                        />
                    </div>
                ))}
        </div>
    );
};