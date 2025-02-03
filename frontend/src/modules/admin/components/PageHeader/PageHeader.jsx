const PageHeader = ({ title, description, buttonText, onButtonClick }) => {
    return (
        <>
            <div className="flex flex-row items-center justify-between pt-10 pb-10">
                <div className="ml-20">
                    <h2 className="text-3xl font-bold text-[#F55F1D]">{title}</h2>
                    <p className="text-sm text-zinc-500">{description}</p>
                </div>
                {buttonText && onButtonClick && (
                    <div className="mr-20">
                        <button
                            type="button"
                            className="bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
                            onClick={onButtonClick}
                        >
                            {buttonText}
                        </button>
                    </div>
                )}
            </div>
            <div className="w-full h-[1px] bg-black"></div>
        </>
    );
};

export default PageHeader;
